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
    
    public static boolean addReservationEntry(String name, String room, DateEntry date, int seats, Timestamp timestamp) {
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
            return true;
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
    
    public static void deleteReservation(String name, String room, int seats, DateEntry date, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addReservationEntry = connection.prepareStatement("INSERT INTO RESERVATIONS"
                + "(FACULTY, ROOM, SEATS, TIMESTAMP, MONTH, DAY, YEARS)" + "VALUES(?,?,?,?,?,?,?");
            addReservationEntry.setString(1, name);
            addReservationEntry.setString(2, room);
            addReservationEntry.setInt(3, seats);
            addReservationEntry.setTimestamp(4, timestamp);
            addReservationEntry.setInt(5, date.getMonth());
            addReservationEntry.setInt(6, date.getDay());
            addReservationEntry.setInt(7, date.getYear());
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
            getReservationByDate = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE (MONTH, DAY, YEAR) LIKE (?,?,?)" + "ORDER BY FACULTY");
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
                resultSet.getInt("year")),
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
            getReservationByFaculty = connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE FACULTY LIKE VALUES(?)" + "ORDER BY FACULTY");
            ResultSet resultSet = getReservationByFaculty.executeQuery();
            while (resultSet.next()) {
                facultyReservationArray.add(new ReservationEntry(
                resultSet.getString("faculty"),
                resultSet.getString("room"),
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

