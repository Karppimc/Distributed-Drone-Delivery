package fi.archi.springrest;

public class Delivery {
    private String id;
    private String pickupLocation;
    private String dropoffLocation;
    private String droneId; // ID of the drone assigned to this delivery

    // Constructors, Getters, and Setters
    public Delivery() {}

    public Delivery(String id, String pickupLocation, String dropoffLocation, String droneId) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.droneId = droneId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public String getDroneId() { return droneId; }
    public void setDroneId(String droneId) { this.droneId = droneId; }
}

