package com.example.evoLog_TP3.logging.profile;

import com.example.evoLog_TP3.logging.loggingParser.LPS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileGenerator {
    private Map<String, ReadProfile> readProfiles = new HashMap<>();
    private Map<String, WriteProfile> writeProfiles = new HashMap<>();
    private Map<String, HighPriceProfile> highPriceProfiles = new HashMap<>();

    private double mostExpensivePrice = 1;
    private ArrayList<LPS> arrayLps;

    public ProfileGenerator(ArrayList<LPS> arrayLps) {
        this.arrayLps = arrayLps;
        Document mostExpensiveProduct = getMostExpensiveProduct();
        if (mostExpensiveProduct != null) {
            this.mostExpensivePrice = mostExpensiveProduct.getDouble("price");
            System.out.println("Most expensive product price: " + this.mostExpensivePrice);
        }
    }

    // MongoDB connection method
    private MongoClient connectToMongoDB() {
        String connectionString = "mongodb+srv://satea:00000000@productappcluster.7n7la.mongodb.net/?retryWrites=true&w=majority&appName=ProductAppCluster";
        return MongoClients.create(connectionString);
    }

    // Method to retrieve the most expensive product from MongoDB
    private Document getMostExpensiveProduct() {
        try (MongoClient mongoClient = connectToMongoDB()) {
            MongoDatabase database = mongoClient.getDatabase("productdb");
            MongoCollection<Document> collection = database.getCollection("products");

            // Query to find the product with the highest price
            Document sort = new Document("price", -1); // Sort by price in descending order
            Document mostExpensiveProduct = collection.find().sort(sort).first(); // Get the first document
            return mostExpensiveProduct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Process each log entry
    public void processLog(LPS lps) {
        String action = lps.getAction();
        String userId = lps.getUserId();
        double price = lps.getPrice();

        if (action.contains("getAllProducts") || action.contains("getProductById")) {
            readProfiles.putIfAbsent(userId, new ReadProfile(userId));
            readProfiles.get(userId).incrementReadOperations();
        } else if (action.contains("addProduct") || action.contains("updateProduct") || action.contains("deleteProduct")) {
            writeProfiles.putIfAbsent(userId, new WriteProfile(userId));
            writeProfiles.get(userId).incrementWriteOperations();
        }
        if (action.contains("getProductById") && price >= mostExpensivePrice) {
            highPriceProfiles.putIfAbsent(userId, new HighPriceProfile(userId));
            highPriceProfiles.get(userId).incrementHighPriceOperations();
        }
    }

    public void processLogs() throws Exception {
        for (LPS lps : arrayLps) {
            processLog(lps);
        }
        saveProfilesAsJson();
    }

    public void saveProfilesAsJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("read_profiles.json"), readProfiles.values());
        mapper.writeValue(new File("write_profiles.json"), writeProfiles.values());
        mapper.writeValue(new File("highPrice_profiles.json"), highPriceProfiles.values());
    }
}
