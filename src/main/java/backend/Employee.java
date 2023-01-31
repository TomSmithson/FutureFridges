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
import org.bson.conversions.Bson;

public class Employee {
    public static void main(String[] args) {
        String uri = "mongodb+srv://TomSmithson:Smithson123@advancedanalysisdesign.hezob.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("futurefridges");
            System.out.println(database.getName());
            for (String name : database.listCollectionNames()) {
                System.out.println(name);
            }
            MongoCollection<Document> collection = database.getCollection("employee");
            
//             Inserting a docuent
//            Document chef1 = new Document("name", "Tom").append("role", "Chef").append("date", "31/01/23");
//            InsertOneResult result = collection.insertOne(chef1);
//            System.out.println("Inserted a document with the following id: " + result.getInsertedId().asObjectId().getValue());
            
            // Finding based on condition
            Bson filter = Filters.and(Filters.eq("role", "Chef"));
            collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
        }
    }  
}
