
import java.sql.Date;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shado
 */
public class WaitlistEntry {

    public String getFaculty() {
        return Faculty;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public WaitlistEntry(String Faculty, DateEntry date, int seats, Timestamp timestamp) {
        this.Faculty = Faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    String Faculty;
    DateEntry date;

    public DateEntry getDate() {
        return date;
    }
    int seats;
    Timestamp timestamp;
}
