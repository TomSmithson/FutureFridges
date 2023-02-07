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

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

// Notes for later
//             Inserting a docuent
//            Document apples = new Document("name", "apple").append("qty", "5").append("date", "31/01/23");
//            InsertOneResult result = collection.insertOne(apples);
//            System.out.println("Inserted a document with the following id: " + result.getInsertedId().asObjectId().getValue());
            
            // Finding based on condition
//            Bson filter = Filters.and(Filters.eq("name", "apple"));
//            collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));


public class DatabaseHandler {
    final String URI = "mongodb+srv://TomSmithson:Smithson123@advancedanalysisdesign.hezob.mongodb.net/?retryWrites=true&w=majority";
    final String DATABASENAME = "futurefridges";
    Bson filter = null;
    boolean connected = false;
    
    ConnectionString connectionString = null;
    MongoClientSettings settings = null;
    MongoClient mongoClient = null;
    MongoDatabase database = null;
    MongoCollection<Document> collection = null;
    
    public DatabaseHandler() {
        connectionString = new ConnectionString(URI);
        settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build())
            .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASENAME);
    }
    
    public MongoCollection<Document> connectToInventory() {
        return database.getCollection("inventory");
    }
    
    public MongoCollection<Document> connectToEmployee() {
        return database.getCollection("employee");
    }
    
    public MongoCollection<Document> connectToUser() {
        return database.getCollection("users");
    }
    
    public MongoCollection<Document> connectToDelivery() {
        return database.getCollection("delivery");
    }
}
