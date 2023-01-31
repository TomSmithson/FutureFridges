/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author tomsm
 */


import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.HashMap;
import org.bson.conversions.Bson;


public class Inventory {
    public static void main(String[] args) {
        String uri = "mongodb+srv://TomSmithson:Smithson123@advancedanalysisdesign.hezob.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("futurefridges");
            System.out.println(database.getName());
            for (String name : database.listCollectionNames()) {
                System.out.println(name);
            }
            MongoCollection<Document> collection = database.getCollection("inventory");
            
//             Inserting a docuent
//            Document apples = new Document("name", "apple").append("qty", "5").append("date", "31/01/23");
//            InsertOneResult result = collection.insertOne(apples);
//            System.out.println("Inserted a document with the following id: " + result.getInsertedId().asObjectId().getValue());
            
            // Finding based on condition
            Bson filter = Filters.and(Filters.eq("name", "apple"));
            collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
        }
    }
    
    String URI = "mongodb+srv://TomSmithson:Smithson123@advancedanalysisdesign.hezob.mongodb.net/?retryWrites=true&w=majority";
    String DATABASENAME = "futurefridges";
    MongoDatabase database = null;
    MongoCollection<Document> collection = null;
    Bson filter = null;
    boolean connected = false;
    
    public Inventory() {
        connectToDatabase();
    }
    
    public void connectToDatabase() {
        MongoClient mongoClient = MongoClients.create(URI);
        database = mongoClient.getDatabase(DATABASENAME);
    }
    
    public void connectToUsers() {
    }
    
    public void connectToInventory() {
        collection = database.getCollection("inventory");
    }
    
    public void connectToEmployee() {
    }
    
    public ArrayList<HashMap<String, String>> getAllInventory() {
        connectToInventory();
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        collection.find().forEach(doc -> {
           HashMap<String, String> a = new HashMap<String, String>();
           a.put("name", doc.get("name").toString());
           a.put("qty", doc.get("qty").toString());
           a.put("date", doc.get("date").toString());
           results.add(a);
        });
        return results;
    }
}
