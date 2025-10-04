package com.example.demo.config;
//importaciones de las dependencias necesarias //
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Declara la clase como un componente de servicio de Spring
@Configuration
public class RabbitConfig {

    public static final String ORDERS_QUEUE = "ordersQueue";
    public static final String RESULTS_QUEUE = "resultsQueue";

    @Bean
    public Queue ordersQueue() {
        return new Queue(ORDERS_QUEUE, false);
    }

    @Bean
    public Queue resultsQueue() {
        return new Queue(RESULTS_QUEUE, true);
    }

    // Convertidor JSON
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
