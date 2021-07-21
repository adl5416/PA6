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
    
    public static void addDate(java.sql.Date date) {
        connection = DBConnection.getConnection();
        try {
            addDate = connection.prepareStatement("INSERT INTO DATES" + "DATE" + "(?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }

        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<java.sql.Date> getAllDates() {
        connection = DBConnection.getConnection();
        ArrayList<java.sql.Date> datesArray = new ArrayList<>();
        try {
            getAllDates = connection.prepareStatement("SELECT * FROM DATES ORDER BY DATE");
            ResultSet resultSet = getAllDates.executeQuery();
            while (resultSet.next()) {
                datesArray.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return datesArray;
    }
}
