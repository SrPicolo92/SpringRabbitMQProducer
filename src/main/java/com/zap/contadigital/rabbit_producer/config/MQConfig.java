package com.zap.contadigital.rabbit_producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE = "message_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";

    //package must be org.springframework.amqp.core.Queue
    //this is the RabbitMQ Queue
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    //this is the Exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    //Here we are binding the RabbitMQ Queue with Exchange
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    //in order to send the message as an Event it must have the JSON format, so we convert it.
    //MessageConverter must come from org.springframework.amqp.support.converter.MessageConverter package
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    //ConnectionFactory must come from org.springframework.amqp.rabbit.connection.ConnectionFactory package
    //this is our RabbitMQ template
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }


}
