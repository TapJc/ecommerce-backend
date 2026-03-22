package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.*;
import com.ecommerce.ecommerce_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Checks if the request body was empty or missing
        if (order == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Order> addOrderItem(@PathVariable long orderId, @RequestParam long productId, @RequestBody OrderItem orderItem) {
         // Find the order by id, returns an Optional<Order>
        return orderRepository.findById(orderId)
                .map(order -> {
                    Product product = productRepository.findById(productId).orElseThrow();
                    // Complete the orderItem by attaching the full Order and Product objects
                    // JPA will extract their ids and save them as foreign keys (order_id, product_id) in the order_item table                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                
                    // Register the fully built orderItem with JPA by adding it to the order's list
                    // CascadeType.ALL will scan this list and automatically INSERT the new orderItem into the order_item table
                    order.getOrderItems().add(orderItem);
                    
                    // Save the order which cascades down and saves the new orderItem automatically
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    // Get a specific order by ID (includes its order items)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
