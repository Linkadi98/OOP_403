package object;



import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGTupleQuery;
import connect.ConnectionAgraph;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQueryResult;


public class Query {
    private String query;
//    private ConnectionAgraph connectionAgraph = new ConnectionAgraph();
    private long checkTime;
//    public Query(String query) {
//        this.query = query;
//    }
    private void printResultQuery(String queryString, String resID){
        ConnectionAgraph connectionAgraph = new ConnectionAgraph(resID);
        AGRepositoryConnection connection = connectionAgraph.getConnection(true);
        long timeStart = System.currentTimeMillis();
        AGTupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        TupleQueryResult result = tupleQuery.evaluate();
        long timeEnd = System.currentTimeMillis();
        checkTime = timeEnd - timeStart;
        try {
            while (result.hasNext()){
                BindingSet bindingSet = result.next();
                Value s = bindingSet.getValue("s");
                Value p = bindingSet.getValue("p");
                Value o = bindingSet.getValue("o");
                System.out.println(s+" "+p+" "+ o);
                System.out.println(s.toString());
            }
        } finally {
            result.close();
        }

        System.out.println("time: "+ checkTime);


    }

    public static void main(String[] args) {
        Query query = new Query();
//        String resID0 = "";
//        String resID1 = "";
//        String resID2 = "";
//        String resID3 = "";
//        String resID4 = "";

        String queryStringSimple0 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple1 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Canada')}";
        String queryStringSimple2 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple3 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple4 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple5 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple6 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple7 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple8 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(?o='Sơn Tùng M-TP')}";
        String queryStringSimple9 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(? o='Sơn Tùng M-TP')}";
        for (int i = 0; i<5; i++){
            String resID = "repository"+i;
            query.printResultQuery(queryStringSimple0,resID);
            query.printResultQuery(queryStringSimple1,resID);
            query.printResultQuery(queryStringSimple2,resID);
            query.printResultQuery(queryStringSimple3,resID);
            query.printResultQuery(queryStringSimple4,resID);
            query.printResultQuery(queryStringSimple5,resID);
            query.printResultQuery(queryStringSimple6,resID);
            query.printResultQuery(queryStringSimple7,resID);
            query.printResultQuery(queryStringSimple8,resID);
            query.printResultQuery(queryStringSimple9,resID);
        }


    }
}

