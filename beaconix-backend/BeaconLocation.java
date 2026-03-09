import org.bson.Document;

public class BeaconLocation {
    private String beaconId;
    private double latitude;
    private double longitude;
    private String timestamp;

    public BeaconLocation(String beaconId, double latitude, double longitude, String timestamp) {
        this.beaconId = beaconId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public Document toDocument() {
        return new Document("beaconId", beaconId)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("timestamp", timestamp);
    }
}
