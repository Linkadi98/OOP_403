package com.gr8.connect;

import com.franz.agraph.http.AGHttpRepoClient;
import com.franz.agraph.repository.*;

public class ConnectionAgraph {
    private static final String SERVER_URL = "http://localhost:10035";
    private static final String CATALOG_ID = "scratch";
    private static final String REPOSITORY_ID = "javatutorial";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "oopgr8";



    public AGRepositoryConnection getConnection(boolean create){
        AGServer server = new AGServer(SERVER_URL,USERNAME,PASSWORD);
        AGCatalog catalog = server.getCatalog(CATALOG_ID);
        AGRepository repository = catalog.createRepository(REPOSITORY_ID);
        repository.initialize();
        AGRepositoryConnection connection = repository.getConnection();
        System.out.println("Connection is up in res: "+repository.getRepositoryID()+" have "+connection.size()+ " statement");
        if (!create){
            return null;
        }
        return connection;
    }
}
