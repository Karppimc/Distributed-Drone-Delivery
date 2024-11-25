package fi.archi.springrest.Device;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommandListener {

    @RabbitListener(queues = "flight-commands")
    public void listenForCommands(String command) {
        System.out.println("Received command: " + command);
        // Simulate drone responding to the command
    }
}
