package com.ecommerce.ecommerce_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Many order items can belong to one order
    // Creates order_id foreign key column in order_item table
    @ManyToOne
    @JoinColumn(name = "order_id")
    // Skip serializing the order field to prevent infinite recursion, as OrderItem is the child in the relationship
    @JsonBackReference
    private Order order;
    
    // Many order items can belong to one product
    // Creates product_id foreign key column in order_item table
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private String arrivalDate;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, String arrivalDate) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.arrivalDate = arrivalDate;
    }

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
