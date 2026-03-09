import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class BeaconDAO {
    private MongoCollection<Document> beaconCollection;
    private MongoCollection<Document> locationCollection;
    private MongoCollection<Document> alertCollection;

    public BeaconDAO() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        beaconCollection = db.getCollection("beacons");
        locationCollection = db.getCollection("locations");
        alertCollection = db.getCollection("alerts");
    }

    public void addBeacon(BeaconDevice beacon) {
        beaconCollection.insertOne(beacon.toDocument());
        System.out.println("✅ Beacon added: " + beacon.toDocument().toJson());
    }

    public void addLocation(BeaconLocation location) {
        locationCollection.insertOne(location.toDocument());
        System.out.println("📍 Location logged: " + location.toDocument().toJson());
    }

    public void addAlert(Alert alert) {
        alertCollection.insertOne(alert.toDocument());
        System.out.println("⚠️ Alert added: " + alert.toDocument().toJson());
    }

    // =========================
    // FETCH OPERATIONS
    // =========================
    public List<Document> getAllBeacons() {
        List<Document> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = beaconCollection.find().iterator()) {
            while (cursor.hasNext()) list.add(cursor.next());
        }
        return list;
    }

    public List<Document> getAllAlerts() {
        List<Document> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = alertCollection.find().iterator()) {
            while (cursor.hasNext()) list.add(cursor.next());
        }
        return list;
    }

    public List<Document> getRecentLocations(String beaconId) {
        List<Document> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = locationCollection
                .find(new Document("beaconId", beaconId))
                .sort(new Document("timestamp", -1))
                .limit(5)
                .iterator()) {
            while (cursor.hasNext()) list.add(cursor.next());
        }
        return list;
    }
}
