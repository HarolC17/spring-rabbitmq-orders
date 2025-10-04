# Gestión de Pedidos con RabbitMQ   

Esta funcionalidad implementa un **módulo de gestión de pedidos** para una aplicación web de comercialización de perfumes.  
El objetivo principal es demostrar cómo un sistema puede procesar órdenes de manera **asíncrona** mediante el uso de colas de mensajes en **RabbitMQ**, asegurando una comunicación **desacoplada, escalable y confiable** entre los diferentes componentes de la aplicación.  

La aplicación está construida con **Spring Boot 3.5.6** y **Java 21**, y expone tanto una **API REST** como una **interfaz web** para interactuar con los pedidos.  

---

## Tecnologías Utilizadas
- **Java 21**  
- **Spring Boot 3.5.6**  
- **Spring AMQP** (integración con RabbitMQ)  
- **RabbitMQ** como broker de mensajería  
- **Thymeleaf** para vistas dinámicas  
- **Bootstrap 5.3.0** para estilos  
- **Maven Wrapper (mvnw)** para gestión de dependencias  

---

## Prerrequisitos
Antes de ejecutar el proyecto asegúrate de contar con:  
- Java **21** o superior instalado.  
- **RabbitMQ Server** corriendo en `localhost:5672` con las credenciales por defecto:  
  - Usuario: `guest`  
  - Contraseña: `guest`  
- (Opcional) **Git** para clonar el repositorio.  

---

## Configuración
La aplicación utiliza los parámetros por defecto de RabbitMQ:  
host: localhost
port: 5672
user: guest
password: guest

yaml
Copiar código
En caso de requerir otros valores, pueden modificarse en el archivo `application.properties` o definirse mediante variables de entorno.  

---

## Componentes Principales

### Configuración
- **RabbitConfig.java**: Define las colas `ordersQueue` y `resultsQueue`, el convertidor JSON (Jackson2JsonMessageConverter) y el `RabbitTemplate` para enviar mensajes.  

### Controladores
- **OrderController.java**: expone la API REST.  
  - `POST /api/orders/send`: envía un pedido a la cola.  
  - `GET /api/orders/results`: devuelve los pedidos procesados.  
- **ViewController.java**: sirve la vista `orders.html`.  

### Modelo
- **Order.java**: entidad que representa un pedido con atributos como `orderId`, `customer`, `perfume`, `quantity` y `total`.  

### Servicios
- **OrderProducer.java**: envía pedidos a `ordersQueue`.  
- **OrderConsumer.java**: escucha `ordersQueue`, procesa pedidos y los reenvía a `resultsQueue`.  
- **ResultService.java**: almacena y expone los pedidos procesados desde `resultsQueue`.  

### Clase Principal
- **DemoApplication.java**: punto de entrada que inicializa el proyecto.  

### Interfaz Web
- **orders.html**: permite crear pedidos mediante un formulario y visualizar resultados en una tabla dinámica con AJAX.  

---

## Flujo de Mensajería

1. **Usuario**: envía un pedido desde la interfaz web `/orders` o vía API.  
2. **OrderController**: recibe el pedido y lo pasa al `OrderProducer`.  
3. **OrderProducer**: envía el mensaje a la cola `ordersQueue`.  
4. **OrderConsumer**: escucha `ordersQueue`, procesa el pedido y lo reenvía a `resultsQueue`.  
5. **ResultService**: guarda el pedido en memoria y lo expone en `/api/orders/results`.  
6. **Interfaz Web**: actualiza dinámicamente la tabla con los pedidos procesados.  

---

## Documentación de apoyo
https://drive.google.com/file/d/1nl0fD68vsUskpFCqEXZlAP3ew_pldaz-/view?usp=sharing
