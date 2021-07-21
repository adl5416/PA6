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

public class Faculty {
    private static Connection connection;
    private static PreparedStatement addFaculty;
    private static PreparedStatement getAllFaculty;

    public static void  addFaculty(String name) {
        connection = DBConnection.getConnection();
        try {
            addFaculty = connection.prepareStatement("INSERT INTO FACULTY" + "NAME" + "VALUES(?)");
            addFaculty.setString(1, name);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllFaculty() {
        connection = DBConnection.getConnection();
        ArrayList<String> facultyArray = new ArrayList<>();
        try  {
            getAllFaculty = connection.prepareStatement("SELECT * FROM FACULTY ORDER BY NAME");
            ResultSet resultSet = getAllFaculty.executeQuery();
            
            while (resultSet.next()) {
                facultyArray.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return facultyArray;
    }
}
