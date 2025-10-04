package com.example.demo.config;
//importaciones de las dependencias necesarias //
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Esta anotación le dice a Spring que esta clase contiene definiciones de beans que deben ser gestionadas por el contenedor de Spring.//
@Configuration
public class RabbitConfig {
//se definen los nombres de las colas como constantes
    public static final String ORDERS_QUEUE = "ordersQueue";
    public static final String RESULTS_QUEUE = "resultsQueue";

    @Bean
    public Queue ordersQueue() {
        return new Queue(ORDERS_QUEUE, false);
    }//los mensajes en esta cola se perderán si el servidor de RabbitMQ se reinicia.

    @Bean
    public Queue resultsQueue() {
        return new Queue(RESULTS_QUEUE, true);
    }//los mensajes persistan en el disco y no se pierdan si el servidor se cae o se reinicia.

    // Convertidor JSON
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }//Convierte objetos de Java a formato JSON para ser enviados a la cola y viceversa,puedan comunicarse fácilmente con JSON.

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);//se utiliza para enviar y recibir mensajes en la aplicación, y nos aseguremos que los mensajes se manejen en formato JSON
        return template;
    }
}
