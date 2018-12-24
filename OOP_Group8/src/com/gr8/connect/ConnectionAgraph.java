package connect;


import com.franz.agraph.repository.*;

public class ConnectionAgraph {
    private static final String SERVER_URL = "http://localhost:10035";
    private static final String CATALOG_ID = "scratch";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "oopgr8";

    public ConnectionAgraph(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    private String repositoryId;


    public AGRepositoryConnection getConnection(boolean create){
        AGServer server = new AGServer(SERVER_URL,USERNAME,PASSWORD);
        AGCatalog catalog = server.getCatalog(CATALOG_ID);
        AGRepository repository = catalog.createRepository(getRepositoryId());
        repository.initialize();
        AGRepositoryConnection connection = repository.getConnection();
        System.out.println("Connection is up in res: "+repository.getRepositoryID()+" have "+connection.size()+ " statement");
        if (!create){
            return null;
        }
        return connection;
    }
}
