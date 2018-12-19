package Data;

import com.github.jsonldjava.core.RDFDataset;

import java.io.*;
import java.net.URL;
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
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> pQ = new PriorityQueue<Node>(500, comparator);///home/nickf2k/OOP/OOP/OOP_403/OOP_Group8/src/main/java
        File file = new File("/Data/demo2.txt");
        URL url = getClass().getResource("demo2.txt");
//        File file1 = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] test = st.split("\t");
            Node node = new Node();
            node.ID = test[0];
            node.priority = node.cutNumberFrom(node.ID);
            String temp = test[1];
            if(temp.equals("labels_vi")) {
                node.type = temp;
                node.label_vi = test[2];
            }
            else if(temp.equals("labels_en")) {
                node.type = temp;
                node.label_en = test[2];
            }
            else if(temp.equals("descriptions_vi")) {
                node.type = temp;
                node.description_vi = test[2];
            }
            else if(temp.equals("descriptions_en")) {
                node.type = temp;
                node.description_en = test[2];
            }
            else if(test[1].equals("alias")) {
                node.type = temp;
                node.alias = test[2];
            }
            else {
                node.relation = test[1];
                node.ID2 = test[2];
            }

            pQ.add(node);

        }

        for (int i = 0; i < pQ.size(); i++) {
            Node newNode = pQ.poll();
            System.out.println(newNode.relation + " " + newNode.ID2);
        }
    }

    public void createURI(PriorityQueue<Node> pQ) {
        // duyet hang doi
        while (!pQ.isEmpty()) {
            // lay ra node dau tien
            Node node = pQ.poll();
            // kiem tra type nao thi tao link do
            if (node.type.equals("labels_vi")) {
                // tao IRI tai day
            }
            else if(false) {
                // cac truong hop khac
            }
        }
    } 

    public static void main (String [] args) throws Exception {
        CreateEntity cr = new CreateEntity();
        cr.createSetFirstName();


    }







}
