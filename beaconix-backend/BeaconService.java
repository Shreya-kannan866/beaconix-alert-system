import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.bson.Document;

public class BeaconService {
    private BeaconDAO dao;

    public BeaconService() {
        dao = new BeaconDAO();
    }

    public void registerBeacon(String id, String name, String status, int battery) {
        BeaconDevice beacon = new BeaconDevice(id, name, status, battery);
        dao.addBeacon(beacon);
        if (battery < 20) {
            triggerAlert(id, "Low Battery", "Battery below 20%");
        }
    }

    public void updateLocation(String beaconId, double lat, double lon) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        BeaconLocation loc = new BeaconLocation(beaconId, lat, lon, timestamp);
        dao.addLocation(loc);
    }

    public void triggerAlert(String beaconId, String type, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Alert alert = new Alert(beaconId, type, message, timestamp);
        dao.addAlert(alert);
    }

    // =========================
    // FETCH METHODS
    // =========================
    public void displayAllBeacons() {
        System.out.println("\n🛰️ All Beacons:");
        for (Document doc : dao.getAllBeacons()) {
            System.out.println(doc.toJson());
        }
    }

    public void displayAllAlerts() {
        System.out.println("\n🚨 All Alerts:");
        for (Document doc : dao.getAllAlerts()) {
            System.out.println(doc.toJson());
        }
    }

    public void displayRecentLocations(String beaconId) {
        System.out.println("\n📍 Recent Locations for " + beaconId + ":");
        for (Document doc : dao.getRecentLocations(beaconId)) {
            System.out.println(doc.toJson());
        }
    }

    // =========================
    // AUTO MONITOR SYSTEM
    // =========================
    public void startAutoMonitor() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n🔁 Auto Monitor: Checking beacons...");
                List<Document> beacons = dao.getAllBeacons();

                for (Document beacon : beacons) {
                    int battery = beacon.getInteger("batteryLevel", 0);
                    String status = beacon.getString("status");
                    String id = beacon.getString("id");

                    if (battery < 20) {
                        triggerAlert(id, "Low Battery", "Battery below 20%");
                    }
                    if ("Inactive".equalsIgnoreCase(status)) {
                        triggerAlert(id, "Disconnected", "Beacon " + id + " is inactive.");
                    }
                }
            }
        }, 0, 10000); // every 10 seconds
    }
}
