// Declaración del paquete para la capa de controlador
package com.example.demo.controller;

// Importación de dependencias necesarias
import org.springframework.stereotype.Controller; // Anotación para marcar la clase como controlador de Spring
import org.springframework.web.bind.annotation.GetMapping; // Anotación para manejar solicitudes HTTP GET

// Declara la clase como un controlador de Spring para manejar vistas
@Controller
public class ViewController {

    /**
     * Endpoint para mostrar la página de gestión de pedidos
     */
    @GetMapping("/orders")
    public String ordersPage() {
        // Devuelve el nombre del archivo HTML (sin la extensión) ubicado en /templates
        return "orders"; // Busca orders.html en la carpeta /templates
    }
}
