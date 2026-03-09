public class Main {
    public static void main(String[] args) {
        BeaconService service = new BeaconService();

        // 1️⃣ Register sample beacons
        service.registerBeacon("B001", "Entrance Beacon", "Active", 85);
        service.registerBeacon("B002", "Hallway Beacon", "Inactive", 15);

        // 2️⃣ Log new locations
        service.updateLocation("B001", 12.9721, 77.5933);
        service.updateLocation("B001", 12.9725, 77.5940);
        service.updateLocation("B002", 12.9719, 77.5938);

        // 3️⃣ Display stored data
        service.displayAllBeacons();
        service.displayRecentLocations("B001");

        // 4️⃣ Start the auto monitor system
        service.startAutoMonitor();

        // Keep running for demo
        try { Thread.sleep(30000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
