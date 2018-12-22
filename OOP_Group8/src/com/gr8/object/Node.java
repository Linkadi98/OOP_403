package com.gr8.object;

public class Node {

    private String ID = "";
    private int priority;
    private String label_vi = "";
    private String label_en = "";
    private String description_vi = "";
    private String description_en = "";
    private String alias = "";
    // day la bien type phuc vu cho viec tao URI
    // voi bien nay co the xem node nay thuoc loai nao vi du
    // P01 labels_vi abc thi bien type la labels_vi ->> URI /example/.../labels_vi/abc

    private String type = "";

    private String relation = "";
    private String ID2 = "";


    public Node() {

    }

    public int cutNumberFrom(String ID) throws NumberFormatException {
        String temp = ID.substring(1);
        return Integer.parseInt(temp);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLabel_vi() {
        return label_vi;
    }

    public void setLabel_vi(String label_vi) {
        this.label_vi = label_vi;
    }

    public String getLabel_en() {
        return label_en;
    }

    public void setLabel_en(String label_en) {
        this.label_en = label_en;
    }

    public String getDescription_vi() {
        return description_vi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getID2() {
        return ID2;
    }

    public void setID2(String ID2) {
        this.ID2 = ID2;
    }

    public String getRelation() {
        return relation;
    }

    public void setDescription_vi(String description_vi) {
        this.description_vi = description_vi;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String createValueTypeIRI(){
        String ontology = "http://gr8.org/OOP/Project/ontology/";
        return ontology+this.type;
    }
    public String getValueOfType() {
        String result;
        String type_1 = type;
        if(label_en!=""){
            return label_en;
        }else if (label_vi!=""){
            return label_vi;
        }else if (description_en!=""){
            result = description_en;
        }else if (description_vi!=""){
            result = description_vi;
        }else {
            result = alias;
        }
        return result;
    }
    public boolean checkID2IsObject(String ID2){
        boolean result;
        if (!ID2.substring(0,1).equals("Q")){
            result = false;
        }
        else {
            try {
                int number = cutNumberFrom(getID2());
                result = true;
            }catch (Exception e){
                result = false;
            }
        }
        return result;
    }
}
