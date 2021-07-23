/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shado
 */
import java.sql.*;
import java.util.*;

public class Dates {
    private static Connection connection;
    private static PreparedStatement addDate;
    private static PreparedStatement getAllDates;
    
    public static void addDate(int month, int day, int year) {
        connection = DBConnection.getConnection();
        try {
            addDate = connection.prepareStatement("INSERT INTO DATES" + "(MONTH, DAY, YEAR)" + "VALUES(?,?,?)");
            addDate.setInt(1, month);
            addDate.setInt(2, day);
            addDate.setInt(3, year);
            addDate.executeUpdate();
        }

        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<DateEntry> getAllDates() {
        connection = DBConnection.getConnection();
        ArrayList<DateEntry> datesArray = new ArrayList<>();
        try {
            getAllDates = connection.prepareStatement("SELECT * FROM DATES ORDER BY (YEAR, MONTH, DAY)");
            ResultSet resultSet = getAllDates.executeQuery();
            while (resultSet.next()) {
                datesArray.add(new DateEntry(
                resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("year")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return datesArray;
    }
}
