package com.ian.davidson.port.scanner.config;

import com.ian.davidson.port.scanner.queue.Consumer;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RabbitConfig {

    private final String queueName;
    private final String topicExchangeName;

    public RabbitConfig(@Value("${rabbit.queue-name}") String queueName,
                        @Value("${rabbit.topic-name-dispatch}") String topicExchangeName){
        this.queueName = queueName;
        this.topicExchangeName = topicExchangeName;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange exchange) {
        //TODO: add unique key for myself via property
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory,
                                             final MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);

        //TODO: test this ish out
        //https://stackoverflow.com/questions/40848773/spring-handling-rabbitmq-messages-concurrently

        container.setConcurrentConsumers(10);
        container.setMaxConcurrentConsumers(50);
        container.setConsecutiveActiveTrigger(1);
        container.setConsecutiveIdleTrigger(1);
        container.setPrefetchCount(5);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final Consumer consumer) {
        return new MessageListenerAdapter(consumer, "consumeMessage");
    }

}
