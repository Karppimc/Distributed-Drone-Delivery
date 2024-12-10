# Distributed Drone Delivery System

This project implements a distributed drone delivery system using RabbitMQ and a service-based architecture. The system consists of three main components:
1. **Device** - Simulates a drone responding to commands via RabbitMQ.
2. **Inventory** - Manages the fleet of drones.
3. **Delivery** - Manages delivery requests and assigns drones.

---

## Prerequisites

Ensure you have the following installed:
- **Java Development Kit (JDK)**: Version 17 or above.
- **Maven**: For building and running the Spring Boot applications.
- **RabbitMQ**: Installed and running locally with the Management Plugin enabled.
- **CURL**: For sending test commands to RabbitMQ and services.

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://gitlab.com/santeri-karppinen-sw-architectures-design-2024/Distributed-Drone-Delivery.git
cd Distributed-Drone-Delivery
```

---

### 2. Start RabbitMQ Server

1. Install RabbitMQ from [https://www.rabbitmq.com/download.html](https://www.rabbitmq.com/download.html).
2. Start RabbitMQ:
   ```bash
   rabbitmq-server
   ```
3. Verify RabbitMQ is running:
   ```bash
   rabbitmqctl status
   ```

---

### 3. Configure RabbitMQ Queue

Ensure the `flight-commands` queue exists:
1. Access the RabbitMQ Management Interface (`http://localhost:15672`).
   - Log in with the default credentials: `guest` / `guest`.
2. Create the `flight-commands` queue:
   - Go to **Queues** and add a new queue named `flight-commands`.

---

### 4. Run Applications

Each application must be run in its own terminal window using Maven:

#### Start Drone Simulator (Device)
```bash
cd Device
mvn spring-boot:run
```

#### Start DroneInventoryService
```bash
cd Inventory
mvn spring-boot:run
```

#### Start DeliveryService
```bash
cd Delivery
mvn spring-boot:run
```

---

## Testing the Applications

### 1. Testing Drone Simulator (RabbitMQ Integration)

Send commands to the `flight-commands` queue using RabbitMQ HTTP API:

#### Start Drone
```bash
curl -i -u guest:guest -H "content-type:application/json" -X POST \
-d '{"properties":{}, "routing_key":"flight-commands", "payload":"{\"command\":\"start-drone\"}", "payload_encoding":"string"}' \
http://localhost:15672/api/exchanges/%2F/amq.default/publish
```

#### Land Drone
```bash
curl -i -u guest:guest -H "content-type:application/json" -X POST \
-d '{"properties":{}, "routing_key":"flight-commands", "payload":"{\"command\":\"land\"}", "payload_encoding":"string"}' \
http://localhost:15672/api/exchanges/%2F/amq.default/publish
```

#### Return Drone to Base
```bash
curl -i -u guest:guest -H "content-type:application/json" -X POST \
-d '{"properties":{}, "routing_key":"flight-commands", "payload":"{\"command\":\"return-to-base\"}", "payload_encoding":"string"}' \
http://localhost:15672/api/exchanges/%2F/amq.default/publish
```

---

### 2. Testing DroneInventoryService (Port 8081)

#### Add a Drone
```bash
curl -d '{"id":"123", "name":"Tom-v1", "capacity":300}' -H "Content-Type: application/json" -X POST http://localhost:8081/dronora/drones
```

#### Get All Drones
```bash
curl http://localhost:8081/dronora/drones
```

#### Get a Specific Drone by ID
```bash
curl http://localhost:8081/dronora/drones/123
```

#### Delete a Drone
```bash
curl -X DELETE http://localhost:8081/dronora/drones/123
```

---

### 3. Testing DeliveryService (Port 8082)

#### Create a Delivery
```bash
curl -d '{"id":"D001", "pickupLocation":"Location A", "dropoffLocation":"Location B", "droneId":"123"}' -H "Content-Type: application/json" -X POST http://localhost:8082/dronora/deliveries
```

#### Get All Deliveries
```bash
curl http://localhost:8082/dronora/deliveries
```

#### Get a Specific Delivery by ID
```bash
curl http://localhost:8082/dronora/deliveries/D001
```

#### Delete a Delivery
```bash
curl -X DELETE http://localhost:8082/dronora/deliveries/D001
```

---

## Summary

This project combines RabbitMQ-based messaging with a service-based architecture to implement a complete distributed drone delivery system. Each component runs independently, allowing for scalable and modular operations.
