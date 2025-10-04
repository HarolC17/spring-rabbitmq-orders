package com.example.demo.model;

import java.io.Serializable;

public class Order implements Serializable {
//para que el objeto pueda ser enviado a través de una red o serializado en JSON, como se hace en la configuración de RabbitMQ
    private Integer orderId;
    private String customer;
    private String perfume;
    private Integer quantity;
    private Double total;

    public Order() {}
//No recibe argumentos y es necesario para que muchas librerías y frameworks (como Spring) puedan crear una instancia de la clase antes de cargarle los datos.
    public Order(Integer orderId, String customer, String perfume, Integer quantity, Double total) {
        this.orderId = orderId;
        this.customer = customer;
        this.perfume = perfume;
        this.quantity = quantity;
        this.total = total;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPerfume() {
        return perfume;
    }
    public void setPerfume(String perfume) {
        this.perfume = perfume;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer='" + customer + '\'' +
                ", perfume='" + perfume + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
