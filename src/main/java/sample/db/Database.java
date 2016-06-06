package sample.db;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import sample.KpiProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 02.06.16.
 */
public class Database {

    private static Database INSTANCE;
    private MongoClient mClient;
    private MongoDatabase mDatabase;

    private Database() {
        mClient = new MongoClient();
        mDatabase = mClient.getDatabase(KpiProperties.getDb());
    }

    public static Database getInstance() {
        if (INSTANCE == null) INSTANCE = new Database();
        return INSTANCE;
    }

    public List<String> getDatabases() {
        List<String> list = new ArrayList<>();
        MongoIterable<String> dbs = mClient.listDatabaseNames();
        for (String db : dbs) list.add(db);
        return list;
    }

    public List<String> getCollections() {
        List<String> list = new ArrayList<>();
        for (String collection : mDatabase.listCollectionNames())
            list.add(collection);
        return list;
    }

    public MongoIterable<Document> getAll(String collectionName) {
        MongoCollection<Document> collection = mDatabase
                .getCollection(collectionName);
        return collection.find();
    }

    public String getAllAsJson(String collectionName) {
        FindIterable<Document> documents = mDatabase.getCollection(collectionName).find();
        StringBuilder builder = new StringBuilder("[");
        for (Document document : documents) {

            builder.append(document.toJson());


            builder.append(",\n");
        }
        builder.delete(builder.length() - 2,builder.length());
        builder.append("]");
        return builder.toString();

    }

    public void deleteObject(String collection, Document bson){
        mDatabase.getCollection(collection).deleteOne(bson);
    }


    public void saveJson(String json, String collectionName) {
        mDatabase.getCollection(collectionName)
                .insertOne(Document.parse(json));
    }

    public void save(Document document, String collectoinName) {
        mDatabase
                .getCollection(collectoinName)
                .insertOne(document);

    }


}
