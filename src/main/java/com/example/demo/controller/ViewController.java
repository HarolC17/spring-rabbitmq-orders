package com.example.demo.controller;
//Declara el paquete donde se encuentra la clase. El nombre controller es una convención común en aplicaciones 
//Spring para agrupar las clases que manejan las solicitudes HTTP.
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/orders")
    public String ordersPage() {
        return "orders"; // busca orders.html en /templates
    }
}

