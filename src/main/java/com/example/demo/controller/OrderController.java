package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderProducer;
import com.example.demo.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;
    private final ResultService resultService;

    public OrderController(OrderProducer orderProducer, ResultService resultService) {
        this.orderProducer = orderProducer;
        this.resultService = resultService;
    }//cada método de este controlador devuelve directamente una respuesta HTTP en formato JSON, sin pasar por una vista HTML.

    /**
     * Enviar un pedido a RabbitMQ
     */
    @PostMapping("/send")
    public ResponseEntity<Order> sendOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


     //Obtener todos los pedidos procesados
    @GetMapping("/results")
    public ResponseEntity<List<Order>> getProcessedOrders() {
        List<Order> orders = resultService.getProcessedOrders();
        return ResponseEntity.ok(orders);
    }
}
