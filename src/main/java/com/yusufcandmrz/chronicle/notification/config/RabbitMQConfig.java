package com.yusufcandmrz.chronicle.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.boot.amqp.autoconfigure.RabbitListenerRetrySettingsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String USER_REGISTERED_QUEUE = "notification.user.registered.queue";
    public static final String USER_REGISTERED_ROUTING_KEY = "user.registered";
    public static final String NOTIFICATION_DLX = "notification.dlx";
    public static final String USER_REGISTERED_DLQ = "notification.user.registered.dlq";
    public static final String USER_REGISTERED_DLQ_ROUTING_KEY = "user.registered.dlq";

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return QueueBuilder
                .durable(USER_REGISTERED_QUEUE)
                .deadLetterExchange(NOTIFICATION_DLX)
                .deadLetterRoutingKey(USER_REGISTERED_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, DirectExchange notificationExchange) {
        return BindingBuilder
                .bind(userRegisteredQueue)
                .to(notificationExchange)
                .with(USER_REGISTERED_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange notificationDeadLetterExchange() {
        return new DirectExchange(NOTIFICATION_DLX);
    }

    @Bean
    public Queue userRegisteredDeadLetterQueue() {
        return new Queue(USER_REGISTERED_DLQ);
    }

    @Bean
    public Binding userRegisteredDeadLetterBinding(
            Queue userRegisteredDeadLetterQueue,
            DirectExchange notificationDeadLetterExchange) {

        return BindingBuilder
                .bind(userRegisteredDeadLetterQueue)
                .to(notificationDeadLetterExchange)
                .with(USER_REGISTERED_DLQ_ROUTING_KEY);
    }
}
