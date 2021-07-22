/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.*;
/**
 *
 * @author shado
 */
public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement getWaitlist;
    
    public static void addWaitlistEntry (String faculty, java.sql.Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addWaitlistEntry = connection.prepareStatement("INSERT INTO WAITLIST" 
                    + "(FACULTY, DATE, SEATS, TIMESTAMP" + "VALUES(?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, timestamp);
            addWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteWaitlistEntry(String faculty, java.sql.Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM WAITLIST" 
                    + "(FACULTY, DATE, SEATS, TIMESTAMP" + "VALUES(?,?,?,?)");
            addWaitlistEntry.setString(1, null);
            addWaitlistEntry.setDate(2, null);
            addWaitlistEntry.setInt(3, 0);
            addWaitlistEntry.setTimestamp(4, null);
            addWaitlistEntry.executeUpdate();
        }    
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(java.sql.Date date) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> dateWaitlistArray = new ArrayList<>();
        try {
            getWaitlistByDate = connection.prepareStatement("SELECT FROM WAITLIST WHERE DATE LIKE (?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getWaitlistByDate.executeQuery();
            while (resultSet.next()) {
                dateWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return dateWaitlistArray;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> facultyWaitlistArray = new ArrayList<>();
        try {
            getWaitlistByDate = connection.prepareStatement("SELECT FROM WAITLIST WHERE FACULTY LIKE (?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getWaitlistByFaculty.executeQuery();
            while (resultSet.next()) {
                facultyWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return facultyWaitlistArray;
    }
    
    // Return the whole waitlist
    public static ArrayList<WaitlistEntry> getWaitlist() {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> getWaitlistArray = new ArrayList<>();
        try {
            getWaitlist = connection.prepareStatement("SELECT * FROM WAITLIST" + "ORDER BY (DATE, TIMESTAMP)");
            ResultSet resultSet = getWaitlist.executeQuery();
            while (resultSet.next()) {
                getWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return getWaitlistArray;
    }
}
