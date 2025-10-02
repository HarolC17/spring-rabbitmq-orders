package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final List<Order> processedOrders = new ArrayList<>();

    @RabbitListener(queues = RabbitConfig.RESULTS_QUEUE)
    public void receiveProcessedOrder(Order order) {
        System.out.println("📥 Pedido procesado recibido en resultsQueue: " + order);
        processedOrders.add(order);
    }

    public List<Order> getProcessedOrders() {
        return processedOrders;
    }
}
