package main;

import object.Query;
import object.StoreData;

import java.io.IOException;

public class Main {
    private static final String REPOSITORY_0 = "repository0";
    private static final String REPOSITORY_1 = "repository1";
    private static final String REPOSITORY_2 = "repository2";
    private static final String REPOSITORY_3 = "repository3";
    private static final String REPOSITORY_4 = "repository4";
    private static final String DATAFILE_0 = "data/file_0.txt";
    private static final String DATAFILE_1 = "data/file_1.txt";
    private static final String DATAFILE_2 = "data/file_2.txt";
    private static final String DATAFILE_3 = "data/file_3.txt";
    private static final String DATAFILE_4 = "data/file_4.txt";

    private void storeTriple() throws IOException {
        StoreData storeData = new StoreData(REPOSITORY_4,"/home/nickf2k/OOP/OOP/OOP_403/OOP_Group8/src/com/gr8/data/"+DATAFILE_4);
        storeData.store();
    }
    public static void main(String[] args) {
        Query query = new Query();
//        String resID0 = "";
//        String resID1 = "";
//        String resID2 = "";
//        String resID3 = "";
//        String resID4 = "";
        // simple query string
        String simpleQueryString0 = "SELECT ?s ?p ?o WHERE{ <http://gr8.org/OOP/Project/object/Q89176713> ?p ?o . }";
        String simpleQueryString1 = "SELECT ?s ?p ?o WHERE{ ?s ?p <http://gr8.org/OOP/Project/object/Q881> .}";
        String simpleQueryString2 = "SELECT ?s ?p ?o  WHERE {?s ?p 'Sơn Tùng M-TP'}";
        String simpleQueryString3 = "SELECT ?s  WHERE {?s <http://gr8.org/OOP/Project/object/P569> '+1973-05-04T00:00:00Z'. }";
        String simpleQueryString4 = "SELECT ?s ?p ?o WHERE{ ?s ?p <http://gr8.org/OOP/Project/object/Q881> .}";
        String simpleQueryString5 = "SELECT ?s ?p ?o WHERE{ <http://gr8.org/OOP/Project/object/Q89176713> ?p ?o . }";
        String simpleQueryString6 = "SELECT ?s  WHERE {?s <http://gr8.org/OOP/Project/object/P19> 'Bình Định'. }";
        String simpleQueryString7 = "SELECT ?s ?o WHERE{ ?s <http://gr8.org/OOP/Project/object/P26> ?o. }";
        String simpleQueryString8 = "SELECT ?s ?p ?o WHERE{ ?s ?p ?o . }limit 10";
        String simpleQueryString9 = "select ?s ?p ?o where {<http://gr8.org/OOP/Project/object/Q7883047> <http://gr8.org/OOP/Project/ontology/labels_en> ?o}";

        // complex query string
        String complexQueryString0 = "SELECT ?s ?p ?o  WHERE {?s ?p ?o .filter(regex(?o, 'Việt Nam'))}";
        String complexQueryString1 = "SELECT ?s ?p ?o WHERE{ ?s ?p ?o .FILTER(regex(?o, 'Dương')) }ORDER BY(?s)";
        String complexQueryString2 = "SELECT ?s ?o { ?s <http://gr8.org/OOP/Project/object/P6> ?o . }";
        String complexQueryString3 = "SELECT ?s ?p ?o WHERE{ ?s ?p ?o .FILTER((?o=\"Mỹ\") || (?o=\"Japan\"))}";
        String complexQueryString4 = "SELECT ?s WHERE{?s ?p ?o.FILTER(?p = <http://gr8.org/OOP/Project/object/P1249>)}";
        String complexQueryString5 = "SELECT ?s ?p ?o WHERE{ ?s ?p ?o .FILTER(regex(?o,\"t\",\"h\"))}limit 10 ";
        String complexQueryString6 = "SELECT ?s ?p ?o WHERE{ ?s ?p ?o .FILTER((?o=\"Mỹ\") || (?o=\"Japan\")&&?p=<http://gr8.org/OOP/Project/ontology/descriptions_vi>)}";
        String complexQueryString7 = "SELECT ?s ?p ?o WHERE  { ?s ?p ?o .FILTER(?p=<http://gr8.org/OOP/Project/ontology/descriptions_vi> && ?o=\"Châu lục\")}";
        String complexQueryString8 = "SELECT ?s WHERE {?s ?p ?o.filter(?p=<http://gr8.org/OOP/Project/object/P27> && ?o=<http://gr8.org/OOP/Project/object/Q881>)}";
        String complexQueryString9 = "SELECT ?o WHERE {?s ?p ?o.filter(?p=<http://gr8.org/OOP/Project/object/P85>)}";

        //truy van don gian
        // ae bo comment doan duoi nay de truy van don gian nhe

//        for (int i = 0; i<5; i++){
//            String resID = "repository"+i;
//            query.setResID(resID);
//            query.printResultQuery(simpleQueryString0);
//            query.printResultQuery(simpleQueryString1);
//            query.printResultQuery(simpleQueryString2);
//            query.printResultQuery(simpleQueryString3);
//            query.printResultQuery(simpleQueryString4);
//            query.printResultQuery(simpleQueryString5);
//            query.printResultQuery(simpleQueryString6);
//            query.printResultQuery(simpleQueryString7);
//            query.printResultQuery(simpleQueryString8);
//            query.printResultQuery(simpleQueryString9);
//        }

        //truy van phuc tap
        for (int i =0; i<5;i++){
            String resID = "repository"+i;
            query.setResID(resID);
            query.printResultQuery(complexQueryString0);
            query.printResultQuery(complexQueryString1);
            query.printResultQuery(complexQueryString2);
            query.printResultQuery(complexQueryString3);
            query.printResultQuery(complexQueryString4);
            query.printResultQuery(complexQueryString5);
            query.printResultQuery(complexQueryString6);
            query.printResultQuery(complexQueryString7);
            query.printResultQuery(complexQueryString8);
            query.printResultQuery(complexQueryString9);
        }
        //ae chu y neu gap loi no-space hoac chi chay dc 1,2 repository thi khoi dong lai server, dat lai vong for de truy van den nhung repository nao chua duoc truy van
    }
}
