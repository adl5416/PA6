
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
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    
    public static void addRoom(String name, int seats) {
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("INSERT INTO ROOMS" + "(NAME, SEATS)" + "VALUES(?,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<RoomEntry> getAllPossibleRooms() {
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> allRooms = new ArrayList<RoomEntry>();
        try {
            getAllPossibleRooms = connection.prepareStatement("SELECT * FROM ROOMS ORDER BY SEATS") ;
            ResultSet resultSet = getAllPossibleRooms.executeQuery();
            while (resultSet.next()) {
                allRooms.add(new RoomEntry(
                resultSet.getString("name"),
                resultSet.getInt("seats")));
                // break
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allRooms;
    }
    
    public static void dropRoom(String name, int seats) {
        connection = DBConnection.getConnection();
        try {
            dropRoom = connection.prepareStatement("DELETE FROM ROOMS WHERE NAME LIKE (?) AND SEATS LIKE (?)");
            dropRoom.setString(1, null);
            dropRoom.setString(2, null);
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
    }
}
