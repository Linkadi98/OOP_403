package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/20/2018
 */

public class Sentence {
    public String[] entity = new String[10];
    public String relation = new String();

    public Sentence(String[] entity, String relation) {
        this.entity = entity;
        this.relation = relation;
    }

    public String[] getEntity() {
        return entity;
    }

    public void setEntity(String[] entity) {
        this.entity = entity;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
