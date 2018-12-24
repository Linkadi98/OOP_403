package object;

import com.franz.agraph.repository.*;
import connect.ConnectionAgraph;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;

import java.io.*;

import java.util.Comparator;

import java.util.PriorityQueue;


public class StoreData {
    private static final String LINK = "http://gr8.org/OOP/Project/";
    private ConnectionAgraph connectionAgraph = new ConnectionAgraph();
    private String resID;
    private Comparator<Node> comparator = new NodeComparator();
    private PriorityQueue<Node> pQ = new PriorityQueue<Node>(500, comparator);
    private String pathData;

    public StoreData(String resID, String pathData) {
        this.resID = resID;
        this.pathData = pathData;
    }

    public PriorityQueue<Node> createQueue(String pathData) throws IOException {
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(500, comparator);
        File file = new File(pathData);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] test = st.split("\t");
            Node node = new Node();
            node.setID(test[0]);
            node.setPriority(node.cutNumberFrom(test[0]));
            String temp = test[1];
            if(temp.equals("labels_vi")) {
                node.setType(temp);
                node.setLabel_vi(test[2]);
            }
            else if(temp.equals("labels_en")) {
                node.setType(temp);
                node.setLabel_en(test[2]);
            }
            else if(temp.equals("descriptions_vi")) {
                node.setType(temp);
                node.setDescription_vi(test[2]);
            }
            else if(temp.equals("descriptions_en")) {
                node.setType(temp);
                node.setDescription_en(test[2]);
            }
            else if(test[1].equals("alias")) {
                node.setType(temp);
                node.setAlias(test[2]);
            }
            else {
                node.setRelation(test[1]);
                node.setID2(test[2]);
            }

            priorityQueue.add(node);

        }

        return priorityQueue;
    }
    public void store() throws IOException {
        this.pQ = createQueue(this.pathData);
        System.out.println(pQ);
        connectionAgraph.setRepositoryId(resID);
        AGRepositoryConnection connection = connectionAgraph.getConnection(true);
        AGValueFactory valueFactory = connection.getValueFactory();



        while (!pQ.isEmpty()) {
            // lay ra node dau tien
            Node node = pQ.poll();
            // kiem tra type nao thi tao link do

            if (!node.getType().equals("")) {
                // luu du lieu dang P * *
                IRI object = valueFactory.createIRI(LINK+"object/"+node.getID());
                IRI typeIRI = valueFactory.createIRI(node.createValueTypeIRI());
                Literal literalValue = valueFactory.createLiteral(node.getValueOfType());
                connection.add(object,typeIRI,literalValue);
            }else {
                // luu du lieu dang Q P *
                IRI subjectIRI = valueFactory.createIRI(LINK+"object/"+node.getID());
                IRI predicateIRI = valueFactory.createIRI(LINK+"object/"+node.getRelation());
                if (node.checkID2IsObject(node.getID2())){
                    IRI objectIRI = valueFactory.createIRI(LINK+"object/"+node.getID2());
                    connection.add(subjectIRI,predicateIRI,objectIRI);
                }else {
                    Literal literalValue = valueFactory.createLiteral(node.getID2());
                    connection.add(subjectIRI,predicateIRI,literalValue);
                }
            }
        }
    }
}
