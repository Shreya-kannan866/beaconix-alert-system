import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                String uri = "mongodb://127.0.0.1:27017";
                mongoClient = MongoClients.create(uri);
                database = mongoClient.getDatabase("beaconix");
                System.out.println("✅ Connected to MongoDB: beaconix");
            } catch (Exception e) {
                System.out.println("❌ MongoDB connection failed: " + e.getMessage());
            }
        }
        return database;
    }
}
