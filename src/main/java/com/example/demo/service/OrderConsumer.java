// Declaración del paquete para la capa de servicio
package com.example.demo.service;

// Importación de dependencias necesarias
import com.example.demo.config.RabbitConfig; // Clase de configuración para RabbitMQ
import com.example.demo.model.Order; // Clase del modelo Order
import org.springframework.amqp.rabbit.annotation.RabbitListener; // Anotación para escuchar mensajes de RabbitMQ
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Plantilla de RabbitMQ para enviar mensajes
import org.springframework.stereotype.Service; // Marca esta clase como un componente de servicio de Spring

@Service
public class OrderConsumer {

    private final RabbitTemplate rabbitTemplate;

    public OrderConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.ORDERS_QUEUE)
    public void receiveOrder(Order order) {
        System.out.println("✅ Pedido recibido: " + order);

        // Simulación de procesamiento
        order.setTotal(order.getQuantity() * 120.0);

        // Reenviamos a resultsQueue
        rabbitTemplate.convertAndSend(RabbitConfig.RESULTS_QUEUE, order);
        System.out.println("📤 Pedido procesado enviado a resultsQueue: " + order);
    }
}
