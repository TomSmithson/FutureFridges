/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import org.bson.Document;

/**
 *
 * @author tomsm
 */
public class Notifications {
    
    DatabaseHandler handler = null;
    MongoCollection<Document> collection = null;
    
    public Notifications() {
        handler = new DatabaseHandler();
    }
    
    public void insertMissingItemNotification(HashMap<String, String> missing) {
        collection = handler.connectToNotifications();
        StringBuilder sb = new StringBuilder();
        sb.append(missing.get("qty"));
        sb.append(" ");
        sb.append(missing.get("name"));
        sb.append(" was missing from the delivery on the ");
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
        String date = df.format(LocalDate.now());
        sb.append(date);
        Document m = new Document("notificationType", "Missing Item").append("date", date).append("notificationString", sb.toString());
        InsertOneResult result = collection.insertOne(m);
    }
    
    public void insertDeletedItemNotification(HashMap<String, String> removed) {
        collection = handler.connectToNotifications();
        StringBuilder sb = new StringBuilder();
        sb.append("Removed ");
        sb.append(removed.get("qty"));
        sb.append(" ");
        sb.append(removed.get("name"));
        sb.append(" from the fridge due to expiry");
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
        String date = df.format(LocalDate.now());
        Document m = new Document("notificationType", "Expiry").append("date", date).append("notificationString", sb.toString());
        InsertOneResult result = collection.insertOne(m);
    }
    
    public void insertInsertionItemNotification(HashMap<String, String> inserted) {
        collection = handler.connectToNotifications();
        StringBuilder sb = new StringBuilder();
        sb.append("Inserted ");
        sb.append(inserted.get("qty"));
        sb.append(" ");
        sb.append(inserted.get("name"));
        sb.append(" into the fridge on the ");
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
        String date = df.format(LocalDate.now());
        sb.append(date);
        Document m = new Document("notificationType", "Insertion").append("date", date).append("notificationString", sb.toString());
        InsertOneResult result = collection.insertOne(m);
    }
    
    public ArrayList<HashMap<String, String>> getAllNotifications() {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        collection = handler.connectToNotifications();
        collection.find().forEach(doc -> {
            HashMap<String, String> a = new HashMap<>();
            a.put("notificationType", doc.get("notificationType").toString());
            a.put("date", doc.get("date").toString());
            a.put("notificationString", doc.get("notificationString").toString());
            results.add(a);
        });
        return results;
    }
    
    public void itemTakenFromFridgeNotification(String name, String qty) {
        collection = handler.connectToNotifications();
        StringBuilder sb = new StringBuilder();
        sb.append(qty);
        sb.append(" ");
        sb.append(name);
        sb.append(" has been taken out from the fridge");
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
        String date = df.format(LocalDate.now());
        Document m = new Document("notificationType", "Chef Removal").append("date", date).append("notificationString", sb.toString());
        InsertOneResult result = collection.insertOne(m);
    }
    
    public void userRemovedNotification(String username) {
        collection = handler.connectToNotifications();
    }
    
}

    