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
public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement deleteReservation;
    private static PreparedStatement getReservationByDate;
    private static PreparedStatement getReservationByFaculty;
    private static PreparedStatement getRoomsReservedByDate;
    
    public static void addReservationEntry(String name, String room, java.sql.Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        try {
            addReservationEntry = connection.prepareStatement("INSERT INTO RESERVATIONS"
                + "(FACULTY, ROOM, DATE, SEATS, TIMESTAMP)" + "VALUES(?,?,?,?,?");
            addReservationEntry.setString(1, name);
            addReservationEntry.setString(2, room);
            addReservationEntry.setDate(3, date);
            addReservationEntry.setInt(4, seats);
            addReservationEntry.setTimestamp(5, timestamp);
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteReservation(String name, String room, java.sql.Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            deleteReservation = connection.prepareStatement("DELETE FROM RESERVATIONS"
                + "(FACULTY, ROOM, DATE, SEATS, TIMESTAMP)" + "VALUES(?,?,?,?,?");
            addReservationEntry.setString(1, null);
            addReservationEntry.setString(2, null);
            addReservationEntry.setDate(3, null);
            addReservationEntry.setInt(4, 0);
            addReservationEntry.setTimestamp(5, null);
            deleteReservation.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationByDate(java.sql.Date date) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> dateReservationArray = new ArrayList<>();
        try {
            getReservationByDate = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE DATE LIKE (?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getReservationByDate.executeQuery();
            while (resultSet.next()) {
                dateReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return dateReservationArray;
    }
    
    public static ArrayList<ReservationEntry> getReservationByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> facultyReservationArray = new ArrayList<>();
        try {
            getReservationByFaculty = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE FACULTY LIKE (?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getReservationByFaculty.executeQuery();
            while (resultSet.next()) {
                facultyReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return facultyReservationArray;
    }
    
    public static ArrayList<String> getRoomsReservedByDate(String room) {
        connection = DBConnection.getConnection();
        ArrayList<String> roomsReservationArray = new ArrayList<>();
        try {
            getRoomsReservedByDate = connection.prepareStatement("SELECT * ROOM FROM RESERVATIONS WHERE DATE LIKE (?)" + "ORDER BY ROOM");
            ResultSet resultSet = getRoomsReservedByDate.executeQuery();
            while (resultSet.next()) {
                roomsReservationArray.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return roomsReservationArray;
    }
}

