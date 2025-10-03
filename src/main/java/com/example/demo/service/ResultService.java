// Declaración del paquete para la capa de servicio
package com.example.demo.service;

// Importación de dependencias necesarias
import com.example.demo.config.RabbitConfig; // Clase de configuración para RabbitMQ
import com.example.demo.model.Order; // Clase del modelo Order
import org.springframework.amqp.rabbit.annotation.RabbitListener; // Anotación para escuchar mensajes de RabbitMQ
import org.springframework.stereotype.Service; // Marca esta clase como un componente de servicio de Spring
import java.util.ArrayList; // Clase para manejar listas dinámicas
import java.util.List; // Interfaz para listas

// Declara la clase como un componente de servicio de Spring
@Service
public class ResultService {

    private final List<Order> processedOrders = new ArrayList<>();

    @RabbitListener(queues = RabbitConfig.RESULTS_QUEUE)
    public void receiveProcessedOrder(Order order) {
        System.out.println("Pedido procesado recibido en resultsQueue: " + order);
        processedOrders.add(order);
    }

    public List<Order> getProcessedOrders() {
        return processedOrders;
    }
}
