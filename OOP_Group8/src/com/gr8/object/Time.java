package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Time{
    private int minutes;
    private int hours;
    private int date;
    private int month;
    private int year;

    public Time() {
    }

    @Override
    public String toString() {
        return "Time: "+ date+"/"
                        + month +"/"
                        + year;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
