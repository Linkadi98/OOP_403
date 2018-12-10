package com.gr8.object;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}