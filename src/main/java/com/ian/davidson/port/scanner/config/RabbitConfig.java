package com.ian.davidson.port.scanner.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;


@Configuration
public class RabbitConfig {

    private final String queueName;
    private final String dispatchExchangeTopicName;
    private final String resultExchangeTopicName;

    public RabbitConfig(@Value("${rabbit.queue-name}") final String queueName,
                        @Value("${rabbit.dispatch-topic}") final String dispatchExchangeTopicName,
                        @Value("${rabbit.result-topic}") final String resultExchangeTopicName){
        this.queueName = queueName;
        this.dispatchExchangeTopicName = dispatchExchangeTopicName;
        this.resultExchangeTopicName = resultExchangeTopicName;
    }



    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange dispatchExchange() {
        return new TopicExchange(dispatchExchangeTopicName);
    }

    @Bean
    TopicExchange resultExchange() {
        return new TopicExchange(resultExchangeTopicName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
