package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Event extends Entity {
    private Person creater;
    public Event(String identifier, String name, String description, String link, Time time, Person creater) {
        super(identifier, name, description, link, time);
        this.creater = creater;

    }

    public Person getCreater() {
        return creater;
    }

    public void setCreater(Person creater) {
        this.creater = creater;
    }
}
