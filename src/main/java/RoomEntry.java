/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shado
 */
public class RoomEntry {

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public RoomEntry(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }
    String name;
    int seats;
}
