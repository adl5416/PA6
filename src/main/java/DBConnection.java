
import java.sql.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shado
 */
public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/RoomSchedulerDBAlex916184516";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";
    private static Connection connection;
    
    public static Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            catch(SQLException sqlException) {
                sqlException.printStackTrace();;
                System.exit(1);
            }
        }
        return connection;
    }
}