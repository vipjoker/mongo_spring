package sample;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by oleh on 02.06.16.
 */
public class KpiProperties {
    private static Properties mPoperties;
    private static File mFile;
    private static final String HOST = "mongo.host";
    private static final String PORT = "mongo.port";
    private static final String DB = "mongo.db";
    private static final String COLLECTION = "mongo.collection";


    static {
        mPoperties = new Properties();
        try {
            URL resource = Properties.class.getResource("/database.properties");
            mFile= new File(resource.getPath());


            mPoperties.load(new FileReader(mFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void save(){
        try {

            mPoperties.store(new FileWriter(mFile),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveHost(String host){
        mPoperties.setProperty(HOST,host);
        save();
    }

    public static String getHost(){
        return mPoperties.getProperty(HOST);
    }

    public static void savePort(String port){
        mPoperties.setProperty(PORT,port);
        save();
    }

    public static String getPort(){
        return mPoperties.getProperty(PORT);
    }

    public static void saveDb(String db){
        mPoperties.setProperty(DB,db);
        save();
    }

    public static String getDb(){
        return mPoperties.getProperty(DB);
    }

    public static void saveCollections(String collectoins){
        mPoperties.setProperty(COLLECTION,collectoins);
        save();
    }

    public static String getCollection(){
        return mPoperties.getProperty(COLLECTION);
    }



}
