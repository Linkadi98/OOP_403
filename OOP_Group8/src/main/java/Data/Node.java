package Data;

public class Node {
    String ID = "";
    public int priority;
    String label_vi = "";
    String label_en = "";
    String description_vi = "";
    String description_en = "";
    String alias = "";

    public Node(String ID) {
        priority = cutNumberFrom(ID);
    }

    public Node() {

    }

    public int cutNumberFrom(String ID) throws NumberFormatException {
        String temp = ID.substring(1,ID.length());
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
}
