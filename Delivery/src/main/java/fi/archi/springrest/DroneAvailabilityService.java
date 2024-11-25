package fi.archi.springrest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DroneAvailabilityService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String droneServiceUrl = "http://localhost:8081/dronora/drones";

    public Drone getDroneById(String droneId) {
        return restTemplate.getForObject(droneServiceUrl + "/" + droneId, Drone.class);
    }
}