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
    
    public static void addReservationEntry(String name, String room, DateEntry date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addReservationEntry = connection.prepareStatement("INSERT INTO RESERVATIONS"
                + "(FACULTY, ROOM, MONTH, DAY, YEARS, SEATS, TIMESTAMP)" + "VALUES(?,?,?,?,?,?,?");
            addReservationEntry.setString(1, name);
            addReservationEntry.setString(2, room);
            addReservationEntry.setInt(3, date.getMonth());
            addReservationEntry.setInt(4, date.getDay());
            addReservationEntry.setInt(5, date.getYear());
            addReservationEntry.setInt(6, seats);
            addReservationEntry.setTimestamp(7, timestamp);
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteReservation(String name, String room, int seats, DateEntry date, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addReservationEntry = connection.prepareStatement("DELETE FROM RESERVATIONS"
                + "(FACULTY, ROOM, SEATS, MONTH, DAY, YEARS, TIMESTAMP)" + "VALUES(?,?,?,?,?,?,?");
            addReservationEntry.setString(1, null);
            addReservationEntry.setString(2, null);
            addReservationEntry.setInt(3, 0);
            addReservationEntry.setInt(4, 0);
            addReservationEntry.setInt(5, 0);
            addReservationEntry.setInt(6, 0);
            addReservationEntry.setTimestamp(7, null);
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationByDate(DateEntry date) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> dateReservationArray = new ArrayList<ReservationEntry>();
        try {
            getReservationByDate = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE (MONTH,DAY,YEARS) LIKE (?,?,?)" + "ORDER BY FACULTY");
            getReservationByDate.setInt(1, date.getMonth());
            getReservationByDate.setInt(2, date.getDay());
            getReservationByDate.setInt(3, date.getYear());
            ResultSet resultSet = getReservationByDate.executeQuery();
            while (resultSet.next()) {
                dateReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
                new DateEntry(
                resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("years")),
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
        ArrayList<ReservationEntry> facultyReservationArray = new ArrayList<ReservationEntry>();
        try {
            getReservationByFaculty = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE FACULTY LIKE VALUES(?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getReservationByFaculty.executeQuery();
            while (resultSet.next()) {
                facultyReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
                new DateEntry(resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("years")),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return facultyReservationArray;
    }
    
    public static ArrayList<ReservationEntry> getReservationByRoom(String room) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> roomsReservationArray = new ArrayList<ReservationEntry>();
        try {
            getReservationByFaculty = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE ROOM LIKE VALUES(?)" + "ORDER BY TIMESTAMP");
            ResultSet resultSet = getReservationByFaculty.executeQuery();
            while (resultSet.next()) {
                roomsReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
                new DateEntry(resultSet.getInt("month"),
                resultSet.getInt("day"),
                resultSet.getInt("years")),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return roomsReservationArray;
    }
}

