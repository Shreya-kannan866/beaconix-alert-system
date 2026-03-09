import org.bson.Document;

public class Alert {
    private String beaconId;
    private String type;
    private String message;
    private String timestamp;

    public Alert(String beaconId, String type, String message, String timestamp) {
        this.beaconId = beaconId;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Document toDocument() {
        return new Document("beaconId", beaconId)
                .append("type", type)
                .append("message", message)
                .append("timestamp", timestamp);
    }
}
