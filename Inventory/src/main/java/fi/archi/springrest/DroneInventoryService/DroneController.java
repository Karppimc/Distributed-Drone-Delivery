package fi.archi.springrest.DroneInventoryService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dronora/drones")
public class DroneController {

    private List<Drone> drones = new ArrayList<>();

    // Info/Ping service
    @GetMapping("/info")
    public String info() {
        return "Drone Fleet Management Service is running";
    }

    // Get all drones
    @GetMapping
    public List<Drone> getAllDrones() {
        return drones;
    }

    // Get a specific drone by ID
    @GetMapping("/{id}")
    public Drone getDroneById(@PathVariable String id) {
        Optional<Drone> drone = drones.stream().filter(d -> d.getId().equals(id)).findFirst();
        return drone.orElse(null);
    }

    // Add a new drone
    @PostMapping
    public Drone addDrone(@RequestBody Drone newDrone) {
        drones.add(newDrone);
        return newDrone;
    }

    // Update a drone's details
    @PutMapping("/{id}")
    public Drone updateDrone(@PathVariable String id, @RequestBody Drone updatedDrone) {
        Drone existingDrone = getDroneById(id);
        if (existingDrone != null) {
            existingDrone.setName(updatedDrone.getName());
            existingDrone.setCapacity(updatedDrone.getCapacity());
        }
        return existingDrone;
    }

    // Delete a drone
    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable String id) {
        drones.removeIf(d -> d.getId().equals(id));
    }
}
