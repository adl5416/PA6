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
    
    public static void addWaitlistEntry (String faculty, DateEntry date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addWaitlistEntry = connection.prepareStatement("INSERT INTO WAITLIST" 
                    + "(FACULTY, MONTH, DAY, YEAR, SEATS, TIMESTAMP" + "VALUES(?,?,?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setInt(2, date.getMonth());
            addWaitlistEntry.setInt(3, date.getDay());
            addWaitlistEntry.setInt(4, date.getYear());
            addWaitlistEntry.setInt(5, seats);
            addWaitlistEntry.setTimestamp(6, timestamp);
            addWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteWaitlistEntry(String faculty, DateEntry date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM WAITLIST" 
                    + "(FACULTY, MONTH, DAY, YEAR, SEATS, TIMESTAMP" + "VALUES(?,?,?,?,?,?)");
            addWaitlistEntry.setString(1, null);
            addWaitlistEntry.setInt(2, 0);
            addWaitlistEntry.setInt(3, 0);
            addWaitlistEntry.setInt(4, 0);
            addWaitlistEntry.setInt(5, 0);
            addWaitlistEntry.setTimestamp(6, null);
            addWaitlistEntry.executeUpdate();
        }    
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(DateEntry date) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> dateWaitlistArray = new ArrayList<>();
        try {
            getWaitlistByDate = connection.prepareStatement("SELECT * WAITLIST WHERE (MONTH,DAY,YEAR) LIKE (?,?,?) ORDER BY FACULTY");
            getWaitlistByDate.setInt(1, date.getMonth());
            getWaitlistByDate.setInt(2, date.getDay());
            getWaitlistByDate.setInt(3, date.getYear());
            ResultSet resultSet = getWaitlistByDate.executeQuery();
            while (resultSet.next()) {
                dateWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                new DateEntry(
                resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("year")),
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
            getWaitlistByFaculty = connection.prepareStatement("SELECT FROM WAITLIST WHERE FACULTY LIKE (?) ORDER BY FACULTY");
            ResultSet resultSet = getWaitlistByFaculty.executeQuery();
            while (resultSet.next()) {
                facultyWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                new DateEntry(resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("year")),
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
            getWaitlist = connection.prepareStatement("SELECT * FROM WAITLIST ORDER BY (YEAR, MONTH, DAY, TIMESTAMP)");
            ResultSet resultSet = getWaitlist.executeQuery();
            while (resultSet.next()) {
                getWaitlistArray.add(new WaitlistEntry(
                resultSet.getString("faculty"),
                new DateEntry(resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("year")),
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
