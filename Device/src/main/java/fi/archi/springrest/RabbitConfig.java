package fi.archi.springrest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue flightCommandsQueue() {
        return new Queue("flight-commands", true); // true for a durable queue
    }
}

