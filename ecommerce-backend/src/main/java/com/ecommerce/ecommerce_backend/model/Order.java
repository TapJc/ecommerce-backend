package com.ecommerce.ecommerce_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
// "order" is a reserved keyword in SQL, so we use "orders" as the table name.
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @org.hibernate.annotations.UuidGenerator
    private String id;

    // mappedBy = "order" delegates foreign key management to the "order" field in OrderItem.
    // CascadeType.ALL ensures any operation on an Order cascades down to its OrderItems.    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // Serialize the orderItems list normally, starting point of the relationship
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private String orderDate;
    private String totalPrice;

    public Order() {
    }

    public Order(String orderDate, String totalPrice){
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }
    
    public String getOrderDate() {
        return orderDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
