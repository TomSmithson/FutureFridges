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
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.HashMap;
import backend.DatabaseHandler;
import com.mongodb.client.result.InsertOneResult;
import java.util.Random;

public class Delivery {
    DatabaseHandler handler = null;
    MongoCollection<Document> collection = null;
    
    public Delivery() {
        handler = new DatabaseHandler();
        generateRandomAuthCodes();
    }
    
    public ArrayList<HashMap<String, String>> getAllAuthCodes() {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        collection = handler.connectToDelivery();
        collection.find().forEach(doc -> {
            HashMap<String, String> a = new HashMap<>();
            a.put("authCode", doc.get("authCode").toString());
            results.add(a);
        });
        return results;
    }
    
    public void generateRandomAuthCodes() {
        collection = handler.connectToDelivery();
        collection.deleteMany(new Document());
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int authCode = r.nextInt((9999 - 100) + 1) + 10;
            Document c = new Document("authCode", authCode);
            InsertOneResult result = collection.insertOne(c);
        }
    }

}