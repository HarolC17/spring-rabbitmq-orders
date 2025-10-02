package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
