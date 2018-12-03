package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Organization extends Entity {
    private Person founder;
    public Organization(String identifier, String name, String description, String link, Person founder) {
        super(identifier, name, description, link);
        this.founder = founder;
    }

    public Person getFounder() {
        return founder;
    }

    public void setFounder(Person founder) {
        this.founder = founder;
    }
}
