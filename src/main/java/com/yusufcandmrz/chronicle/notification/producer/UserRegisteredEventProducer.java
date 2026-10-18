package com.yusufcandmrz.chronicle.notification.producer;

import com.yusufcandmrz.chronicle.notification.config.RabbitMQConfig;
import com.yusufcandmrz.chronicle.notification.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserRegisteredEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(UserRegisteredEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.USER_REGISTERED_ROUTING_KEY,
                event
        );
    }

}
