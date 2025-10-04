// Declaración del paquete para la capa de controlador
package com.example.demo.controller;

// Importación de dependencias necesarias
import com.example.demo.model.Order; // Clase del modelo Order
import com.example.demo.service.OrderProducer; // Servicio para enviar pedidos a RabbitMQ
import com.example.demo.service.ResultService; // Servicio para obtener pedidos procesados
import org.springframework.http.HttpStatus; // Clase para códigos de estado HTTP
import org.springframework.http.ResponseEntity; // Clase para respuestas HTTP
import org.springframework.web.bind.annotation.*; // Anotaciones para definir endpoints REST
import java.util.List; // Interfaz para listas

// Declara la clase como un controlador REST de Spring
@RestController
// Define la ruta base para todos los endpoints del controlador
@RequestMapping("/api/orders")
public class OrderController {

    // Instancia del servicio para enviar pedidos
    private final OrderProducer orderProducer;
    // Instancia del servicio para obtener pedidos procesados
    private final ResultService resultService;

    // Inyección de dependencias mediante el constructor
    public OrderController(OrderProducer orderProducer, ResultService resultService) {
        this.orderProducer = orderProducer; // Inicializa el servicio OrderProducer
        this.resultService = resultService; // Inicializa el servicio ResultService
    }

    // Cada metodo de este controlador devuelve directamente una respuesta HTTP en formato JSON, sin pasar por una vista HTML.

    /**
     * Endpoint para enviar un pedido a RabbitMQ
     * @param order Objeto Order recibido en el cuerpo de la solicitud
     * @return Respuesta HTTP con el pedido enviado y estado CREATED (201)
     */
    @PostMapping("/send")
    public ResponseEntity<Order> sendOrder(@RequestBody Order order) {
        // Envía el pedido al servicio OrderProducer para su procesamiento
        orderProducer.sendOrder(order);
        // Devuelve una respuesta HTTP con el pedido y el estado 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * Endpoint para obtener todos los pedidos procesados
     * @return Respuesta HTTP con la lista de pedidos procesados y estado OK (200)
     */
    @GetMapping("/results")
    public ResponseEntity<List<Order>> getProcessedOrders() {
        // Obtiene la lista de pedidos procesados desde el servicio ResultService
        List<Order> orders = resultService.getProcessedOrders();
        // Devuelve una respuesta HTTP con la lista de pedidos y el estado 200 (OK)
        return ResponseEntity.ok(orders);
    }
}
