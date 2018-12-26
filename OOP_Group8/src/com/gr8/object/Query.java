package object;



import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGTupleQuery;
import connect.ConnectionAgraph;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQueryResult;

import javax.security.auth.Subject;


public class Query {
    private ConnectionAgraph connectionAgraph;
    private long checkTime;
    private String resID;
    public String getResID() {
        return resID;
    }
    public void setResID(String resID) {
        this.resID = resID;
    }

    public long printResultQuery(String queryString){
        // phuong thuc nay de in ket qua truy van, tra ve thoi gian truy v
        connectionAgraph = new ConnectionAgraph(resID);
        AGRepositoryConnection connection = connectionAgraph.getConnection(true);
        long timeStart = System.currentTimeMillis();
        AGTupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        TupleQueryResult result = tupleQuery.evaluate();
        long timeEnd = System.currentTimeMillis();
        checkTime = timeEnd - timeStart;
        Value s = null;
        Value p = null;
        Value o = null;
        try {
            while (result.hasNext()){
                BindingSet bindingSet = result.next();
                if (bindingSet.getValue("s")!=null){
                    s = bindingSet.getValue("s");
                }
                if (bindingSet.getValue("p")!=null){
                    p = bindingSet.getValue("p");
                }
                if (bindingSet.getValue("o")!=null){
                    o = bindingSet.getValue("o");
                }

//                System.out.println(s+" "+p+" "+ o);
//                System.out.println(s.toString());
            }
        } finally {
            result.close();
        }

        System.out.println("time: "+ checkTime);
        connection.close();
        return checkTime;
    }
}

