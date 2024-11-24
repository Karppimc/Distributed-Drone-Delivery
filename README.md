# Distributed Drone Delivery System

This project implements a distributed drone delivery system using RabbitMQ for message communication. The application simulates a drone that responds to commands such as `start-drone`, `land`, and `return-to-base`.

## Prerequisites

Before running the application, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 17 or above.
2. **Maven**: For building and running the Spring Boot application.
3. **RabbitMQ**: Installed and running locally. Ensure the RabbitMQ Management Plugin is enabled.
4. **cURL**: For sending test commands to RabbitMQ.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://gitlab.com/santeri-karppinen-sw-architectures-design-2024/Distributed-Drone-Delivery.git
cd Distributed-Drone-Delivery
```

### 2. Install RabbitMQ

- Download and install RabbitMQ from [https://www.rabbitmq.com/download.html](https://www.rabbitmq.com/download.html).
- Start RabbitMQ:
  ```bash
  rabbitmq-server
  ```
- Verify RabbitMQ is running:
  ```bash
  rabbitmqctl status
  ```

### 3. Configure RabbitMQ

Ensure the `flight-commands` queue exists:

1. Access the RabbitMQ Management Interface:
   - Open a browser and navigate to `http://localhost:15672`.
   - Log in with the default credentials: `guest` / `guest`.
2. Create a queue:
   - Go to the **Queues** tab.
   - Click **Add a new queue** and enter `flight-commands` as the name.
   - Leave other fields as default and click **Add queue**.

### 4. Build and Run the Application
 - USE OWN TERMINAL FOR INVENTORY DELIVERY AND DEVICE
1. Navigate to the project directory:
   ```bash
   cd Device or cd Delivery or cd Intentory
   ```
2. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start and listen for messages from the `flight-commands` queue.

### 5. Test the Application

You can send commands to the `flight-commands` queue using the RabbitMQ HTTP API and `cURL`:

#### Send a Command

```bash
curl -i -u guest:guest -H "content-type:application/json" -X POST \
-d '{"properties":{}, "routing_key":"flight-commands", "payload":"{\"command\":\"start-drone\"}", "payload_encoding":"string"}' \
http://localhost:15672/api/exchanges/%2F/amq.default/publish
```

Replace `start-drone` with `land` or `return-to-base` to test other commands.

#### Expected Output

In the application logs, you should see:

```plaintext
Received command: {"command":"start-drone"}
```

For the `land` and `return-to-base` commands, the output will reflect the respective commands.

## Viewing Results

The application logs the received commands in the console where the Spring Boot application is running.

## Troubleshooting

### Common Issues

1. **RabbitMQ Queue Not Found**:
   - Ensure the `flight-commands` queue is created in RabbitMQ.

2. **Connection Issues**:
   - Ensure RabbitMQ is running and accessible at `localhost:5672`.
   - Verify RabbitMQ credentials (`guest:guest`) are correct.

3. **Command Not Received**:
   - Check if the queue has the correct name (`flight-commands`).
   - Confirm the application is running and listening.


## Contact

For any questions or feedback, feel free to reach out to **[Santeri Karppinen]** at **santeri.karppinen@tuni.fi**.
