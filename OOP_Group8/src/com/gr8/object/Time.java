package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Time {
    private static Time ourInstance = new Time();

    public static Time getInstance() {
        return ourInstance;
    }

    private Time() {
    }
}
