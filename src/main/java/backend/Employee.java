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

public class Employee {
    DatabaseHandler handler = null;
    MongoCollection<Document> collection = null;
    
    public Employee() {
        handler = new DatabaseHandler();
    }
    
    public ArrayList<HashMap<String, String>> getAllEmployee() {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        collection = handler.connectToEmployee();
        collection.find().forEach(doc -> {
            HashMap<String, String> a = new HashMap<>();
            a.put("name", doc.get("name").toString());
            a.put("role", doc.get("role").toString());
            a.put("date", doc.get("date").toString());
            results.add(a);
        });
        return results;
    }
    
//    public String createNewUser(String username, String password) {
//        collection = handler.connectToEmployee();
//        Document apples = new Document("username", username).append("password", password);
//        InsertOneResult result = collection.insertOne(apples);
//        System.out.println("Inserted a document with the following id: " + result.getInsertedId().asObjectId().getValue());
//        return "Successfully created a new user with id: " + result.getInsertedId().asObjectId().getValue();
//    }
}
