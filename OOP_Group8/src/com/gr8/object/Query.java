package object;


import connect.ConnectionAgraph;

public class Query {
    private String query;
    private ConnectionAgraph connectionAgraph = new ConnectionAgraph();

    public Query(String query) {
        this.query = query;
    }
    private void printResultQuery(){

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("hello world");
        long endTime = System.currentTimeMillis();
        System.out.println("Took: "+(endTime-startTime)+" ms");
    }
}

