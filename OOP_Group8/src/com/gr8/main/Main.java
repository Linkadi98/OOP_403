package main;

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


    public static void main(String[] args) throws IOException {
        StoreData storeData = new StoreData("repository0","/home/nickf2k/OOP/OOP/OOP_403/OOP_Group8/src/com/gr8/data/file_0.txt");
        storeData.store();
    }
}
