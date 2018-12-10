package connect;

import com.franz.agraph.repository.*;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Statement;

import org.eclipse.rdf4j.repository.RepositoryResult;

import java.io.File;
import java.util.List;

public class TutorialExample {
    private static final String SERVER_URL = "http://localhost:10035";
    private static final String CATALOG_ID = "scratch";
    private static final String REPOSITORY_ID = "javatutorial";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "oopgr8";
    private static final File DATA_DIR = new File(".");

    private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    /**
     * Creating a Repository
     */
    public static AGRepositoryConnection example1(boolean close) throws Exception {
        // Tests getting the repository up.
        System.out.println("\nStarting example1().");
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        System.out.println("Available catalogs: " + server.listCatalogs());
        AGCatalog catalog = server.getRootCatalog();
        System.out.println("Available repositories in catalog " + catalog.getCatalogName() +":" + catalog.listRepositories());

        //create reposotory
        AGRepository myRepository = catalog.createRepository(REPOSITORY_ID);
        myRepository.initialize();
        System.out.println("Creat repository: "+ myRepository.getRepositoryID());
        System.out.println("Repository is writeable:" +myRepository.isWritable());

        //create connect object
        AGRepositoryConnection connection = myRepository.getConnection();
        closeBeforeExit(connection);
        System.out.println("Got a connection");
        System.out.println("Repository " +myRepository.getRepositoryID() + " is up! It contains: " + connection.size() + " statement");

        //show indices
        List<String> indices = connection.listValidIndices(); //valid indices
        System.out.println("Valid indices: "+ indices);
        indices = connection.listIndices();
        System.out.println("Current indices: "+ indices);
        connection.dropIndex("gospi");
        connection.dropIndex("gposi");
//        connection.dropIndex("gspoi");
        connection.addIndex("gspoi");
        indices = connection.listIndices();
        System.out.println("Current indices after removing: "+ indices);


        if (close){
            connection.close();
            myRepository.shutDown();
            return null;
        }
        return connection;

    }
    public static AGRepositoryConnection example2(boolean close) throws Exception {

        //assert some statement and count them
        AGRepositoryConnection connection = example1(false);

//        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
//        AGCatalog catalog = server.getRootCatalog();
//        AGRepositoryConnection connection = catalog.openRepository(REPOSITORY_ID).getConnection();

        AGValueFactory valueFactory = connection.getValueFactory();
        IRI vuong = valueFactory.createIRI("http://example.org/people/vuong");
        IRI linh = valueFactory.createIRI("http://example.org/people/linh");
        IRI person = valueFactory.createIRI("http://example.org/ontology/Person");
        IRI name = valueFactory.createIRI("http://example.org/ontology/name");
        IRI type = valueFactory.createIRI("http://example.org/ontology/type");
        Literal vuongName = valueFactory.createLiteral("Vuong");
        Literal linhName = valueFactory.createLiteral("Linh");
        System.out.println("Triples count before insert: " + connection.size());
        connection.add(vuong, name, vuongName); //vuong's name is "Vuong
        connection.add(vuong, type, person); //vuong is person
        connection.add(linh, name, linhName); //linh's name is Linh
        connection.add(linh, type, person); //linh's is person

        System.out.println("Triples count before insert: " + connection.size()); //in ra size = 4
        RepositoryResult<Statement> result = connection.getStatements(null,null,null,false); //den dong lenh nay chuong trinh ko chay nua
        while (result.hasNext()){
            Statement statement = result.next();
            System.out.println(statement);
        }

        //remove 1 statement

//            connection.remove(vuong, null, null);
//            connection.remove(linh, null, null);
            System.out.println("Triples count before insert: " + connection.size());

            connection.close();


            return null;
    }






    private static void closeBeforeExit(AGRepositoryConnection connection) {
//        if (connection.isActive()){
//            connection.close();
//        }
        System.out.println("Close connection: done!");
    }

    public static void main(String[] args) throws Exception {
//        example1(true);
        example2(false);
    }

}
