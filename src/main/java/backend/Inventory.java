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


public class Inventory {
    
    DatabaseHandler handler = null;
    MongoCollection<Document> collection = null;
    
    public Inventory() {
        handler = new DatabaseHandler();
    }
    
    public ArrayList<HashMap<String, String>> getAllInventory() {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        
        collection = handler.connectToInventory();
        
        collection.find().forEach(doc -> {
           HashMap<String, String> a = new HashMap<>();
           a.put("name", doc.get("name").toString());
           a.put("qty", doc.get("qty").toString());
           a.put("date", doc.get("date").toString());
           results.add(a);
        });
        return results;
    }
}
