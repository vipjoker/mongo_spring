package sample.db;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BSON;
import org.bson.Document;
import sample.KpiProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by oleh on 02.06.16.
 */
public class Database {

    private static Database INSTANCE;
    private MongoClient mClient;
    private MongoDatabase mDatabase;
    public static Database getInstance(){
        if(INSTANCE == null) INSTANCE = new Database();
        return INSTANCE;
    }

    private Database() {
        mClient = new MongoClient();
        mDatabase = mClient.getDatabase(KpiProperties.getDb());
    }

    public List<String> getDatabases(){
        List<String> list = new ArrayList<>();
        MongoIterable<String> dbs = mClient.listDatabaseNames();
        for(String db :dbs)list.add(db);
        return list;
    }

    public List<String> getCollections(){
        List<String> list = new ArrayList<>();
        for(String collection : mDatabase.listCollectionNames())
            list.add(collection);
        return list;
    }

    public MongoIterable<Document> getAll(){
        MongoCollection<Document> collection = mDatabase
                                                .getCollection(KpiProperties.getCollection());
        return collection.find();
    }

    public void save (Document document){
        mDatabase
                .getCollection(KpiProperties.getCollection())
                .insertOne(document);

    }


}
