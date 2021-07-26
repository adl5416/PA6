
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shado
 */
public class DateEntry {
    private int month;
    private int day;
    private int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DateEntry(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    public static boolean CheckDate(int month, int day, int year) {
        Calendar calendar = Calendar.getInstance();
        if (month == 2 && day >= 29) {
            return false;
        }
        if (year < calendar.get(Calendar.YEAR) || month < calendar.get(Calendar.MONTH) || day < calendar.get(Calendar.DATE)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String output = getMonth() + " " + getDay() + " " + getYear();
        return output;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof DateEntry) {
            final DateEntry f = (DateEntry) o;
            if (f.getMonth() == month && f.getDay() == day && f.getYear() == year) {
                return true;
            }
        }
        return false;
    }
    
}
