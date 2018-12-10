package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Country extends Entity {
    private String diachi;
    public Country(String identifier, String name, String description, String link) {
        super(identifier, name, description, link);

    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
