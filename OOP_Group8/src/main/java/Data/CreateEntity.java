package Data;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class CreateEntity {
    private Set<String> setFirstName = new HashSet<String>();
    private Set<String> setMiddleName = new HashSet<String>();
    private Set<String> setLasttName = new HashSet<String>();

    private static final String SERVER_URL = "http://localhost:10035";
    private static final String CATALOG_ID = "scratch";
    private static final String REPOSITORY_ID = "javatutorial";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "oopgr8";
    private static final File DATA_DIR = new File(".");

    private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    public void createSetFirstName() throws IOException {
        File file = new File("/home/phamngocminh/OOP_403/OOP_Group8/src/main/java/Data/test.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] test = st.split("\t");
            for (String s : test) {
                System.out.println(s);
            }
        }
    }

    public static void main (String [] args) throws Exception {
//        CreateEntity cr = new CreateEntity();
//        cr.createSetFirstName();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> pQ = new PriorityQueue<Node>(500, comparator);
    }







}
