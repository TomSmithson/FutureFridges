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
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import static java.time.temporal.ChronoUnit.DAYS;

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
                       
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
            LocalDate date = LocalDate.parse(doc.get("date").toString(), df);
            LocalDate now = LocalDate.now();           
            long days = DAYS.between(now, date);
           
            a.put("expiryDays", Long.toString(days));
           
            results.add(a);
        });
        
        return results;
    }
    
    public ArrayList<HashMap<String, String>> getItemsToBeInserted() {
        ArrayList<HashMap<String, String>> items = getAllInventory();
        ArrayList<HashMap<String, String>> itemsToOrder = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (Integer.parseInt(items.get(i).get("expiryDays")) <= 3) {
                // Order new item
                items.get(i).put("status", "n");
                itemsToOrder.add(items.get(i));
            }
        }
        return itemsToOrder;
    }
    
    public void insertItem(HashMap<String, String> item) {
        collection = handler.connectToInventory();
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/yy").toFormatter();
        String date = df.format(LocalDate.now()).toString();
        Document i = new Document("name", item.get("name")).append("qty", item.get("qty")).append("date", date);
        InsertOneResult result = collection.insertOne(i);   
    }
}
