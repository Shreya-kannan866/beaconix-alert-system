import org.bson.Document;

public class BeaconDevice {
    private String id;
    private String name;
    private String status;
    private int batteryLevel;

    public BeaconDevice(String id, String name, String status, int batteryLevel) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.batteryLevel = batteryLevel;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("name", name)
                .append("status", status)
                .append("batteryLevel", batteryLevel);
    }
}
