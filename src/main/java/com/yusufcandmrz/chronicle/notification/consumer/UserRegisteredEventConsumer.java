package com.yusufcandmrz.chronicle.notification.consumer;

import com.yusufcandmrz.chronicle.notification.config.RabbitMQConfig;
import com.yusufcandmrz.chronicle.notification.event.UserRegisteredEvent;
import com.yusufcandmrz.chronicle.notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventConsumer {

    private final EmailService emailService;

    public UserRegisteredEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_REGISTERED_QUEUE)
    public void consume(UserRegisteredEvent event) {
        emailService.sendWelcomeEmail(
                event.email(), event.username()
        );
    }
}
