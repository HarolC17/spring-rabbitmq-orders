// Declaración del paquete para la capa de servicio
package com.example.demo.service;

// Importación de dependencias necesarias
import com.example.demo.config.RabbitConfig; // Clase de configuración para RabbitMQ
import com.example.demo.model.Order; // Clase del modelo Order
import org.springframework.amqp.rabbit.annotation.RabbitListener; // Anotación para escuchar mensajes de RabbitMQ
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Plantilla de RabbitMQ para enviar mensajes
import org.springframework.stereotype.Service; // Marca esta clase como un componente de servicio de Spring

// Declara la clase como un componente de servicio de Spring
@Service
public class OrderConsumer {

    // Instancia de RabbitTemplate para enviar mensajes a RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    // Inyección del RabbitTemplate mediante el constructor
    public OrderConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate; // Inicializa el RabbitTemplate
    }
    
    // Metodo para escuchar mensajes de la cola de pedidos configurada
    @RabbitListener(queues = RabbitConfig.ORDERS_QUEUE)
    public void receiveOrder(Order order) {
        // Imprime en consola la confirmación de que se recibió el pedido
        System.out.println("Pedido recibido: " + order);

        // Reenvía el pedido a la cola de resultados usando RabbitTemplate
        rabbitTemplate.convertAndSend(RabbitConfig.RESULTS_QUEUE, order);
        // Imprime en consola la confirmación de que el pedido fue enviado a la cola de resultados
        System.out.println("Pedido procesado enviado a resultsQueue: " + order);
    }
}
