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
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.HashMap;
import backend.DatabaseHandler;
import com.mongodb.client.model.Filters;
import java.util.concurrent.atomic.AtomicReference;

public class User {
    //Must Have ////////////////////////////////////////
    /*
    * A login system that requires each user account to have an email, password,
    * job role and name. New users must also be able to be entered into the system.
    *
    * Separate account types for each job role that have different permissions on
    * what they can do on the system. There needs to be three one for delivery drivers,
    * standard chefs and the head chef. Delivery drivers should only be able to insert
    * items into the system while standard chefs and head chefs should be able to both
    * remove and insert items into the system.
    */
    
    DatabaseHandler handler = null;
    MongoCollection<Document> collection = null;
    Bson filter = null;
    
    public User() {
        handler = new DatabaseHandler();
    }
    
    public String login(String username, String password) {
        AtomicReference<String> wrapper = new AtomicReference<>("");
        
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        collection = handler.connectToUser();
        filter = Filters.and(Filters.eq("username", username), Filters.eq("password", password));
        collection.find(filter).forEach(doc -> {
            System.out.println(doc);
            wrapper.set("authenticated");
        });
        return wrapper.get();
    }
    
    

    //Should have //////////////////////////////////

    /*
    Deny use for anyone who is not approved to use the system and make sure that the
    user can only complete actions based on their user role. For example a delivery driver
    should be denied from removing items from the system.
     */
}
