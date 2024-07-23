package com.ian.davidson.port.scanner.config;

import com.ian.davidson.port.scanner.queue.Receiver;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RabbitConfig {

//    private final String dispatchExchangeTopicName = ;
//    private final String resultExchangeTopicName;

//    public RabbitConfig(@Value("${rabbit.dispatch-queue-name}") final String dispatchExchangeTopicName,
//                        @Value("${rabbit.result-queue-name}") final String resultExchangeTopicName){
//        this.queueName = queueName;
//        this.dispatchExchangeTopicName = dispatchExchangeTopicName;
//        this.resultExchangeTopicName = resultExchangeTopicName;
//    }

    private final String queueName = "queue";
    private final String topicExchangeName = "dispatch";


    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        //TODO: add unique key for myself
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
