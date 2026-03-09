package com.beaconix.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "beaconix";

    public static MongoDatabase getDatabase() {
        if (database == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DB_NAME);
            System.out.println("Connected to MongoDB database: " + DB_NAME);
        }
        return database;
    }
}
