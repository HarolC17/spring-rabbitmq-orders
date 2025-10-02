package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
