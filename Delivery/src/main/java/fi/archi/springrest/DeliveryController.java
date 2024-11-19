package fi.archi.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dronora/deliveries")
public class DeliveryController {

    private final List<Delivery> deliveries = new ArrayList<>();

    @Autowired
    private DroneAvailabilityService droneAvailabilityService;

    @PostMapping
public Delivery createDelivery(@RequestBody Delivery newDelivery) {
    // Check if a delivery with the same ID already exists
    Optional<Delivery> existingDelivery = deliveries.stream()
                                                    .filter(d -> d.getId().equals(newDelivery.getId()))
                                                    .findFirst();
    if (existingDelivery.isPresent()) {
        throw new RuntimeException("Delivery with ID " + newDelivery.getId() + " already exists");
    }

    Drone assignedDrone = droneAvailabilityService.getDroneById(newDelivery.getDroneId());
    if (assignedDrone == null) {
        throw new RuntimeException("Drone not available");
    }

    deliveries.add(newDelivery);
    return newDelivery;
}

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveries;
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable String id) {
        return deliveries.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable String id) {
        deliveries.removeIf(d -> d.getId().equals(id));
    }
}