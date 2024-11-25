package fi.archi.springrest.Device;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatusPublisher {

    private final RabbitTemplate rabbitTemplate;

    public StatusPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStatus(String status) {
        rabbitTemplate.convertAndSend("status-updates", status);
        System.out.println("Sent status: " + status);
    }
}
