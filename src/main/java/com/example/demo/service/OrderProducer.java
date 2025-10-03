// Importación de dependencias necesarias
import com.example.demo.config.RabbitConfig; // Clase de configuración para RabbitMQ
import com.example.demo.model.Order; // Clase del modelo Order
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Plantilla de RabbitMQ para enviar mensajes
import org.springframework.stereotype.Service; // Marca esta clase como un componente de servicio de Spring

// Declara la clase como un componente de servicio de Spring
@Service
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(RabbitConfig.ORDERS_QUEUE, order);
        System.out.println("📤 Pedido enviado a RabbitMQ: " + order);
    }
}
