// Importación de dependencias necesarias
import com.example.demo.config.RabbitConfig; // Clase de configuración para RabbitMQ
import com.example.demo.model.Order; // Clase del modelo Order
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Plantilla de RabbitMQ para enviar mensajes
import org.springframework.stereotype.Service; // Marca esta clase como un componente de servicio de Spring

// Declara la clase como un componente de servicio de Spring
@Service
    // Instancia de RabbitTemplate para enviar mensajes a RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    // Inyección del RabbitTemplate mediante el constructor
    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate; // Inicializa el RabbitTemplate
    }

    // Método para enviar un pedido a la cola de pedidos
    public void sendOrder(Order order) {
        // Envía el pedido a la cola de pedidos configurada usando RabbitTemplate
        rabbitTemplate.convertAndSend(RabbitConfig.ORDERS_QUEUE, order);
        // Imprime en consola la confirmación de que el pedido fue enviado
        System.out.println("Pedido enviado a RabbitMQ: " + order);
    }
}
