package com.gr8.object;

import sun.plugin.javascript.navig4.Link;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public abstract class Entity {
    /**
     * This is a abstract class
     * This is a parent-class of any object
     */
    public String identifier;
    public String name;
    public String description;
    public String link;
    public Time time;

    public Entity(String identifier, String name, String description, String link, Time time) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
        this.link = link;
        this.time = time;
    }

    public Entity(String identifier, String name, String description, String link) {
    }

    public String source(){
        return "Link: "+this.link+"\n"+"Time: "+this.time.toString();
    }
}
