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
    public ResponseEntity<Order> addOrderItem(@PathVariable String orderId, @RequestParam long productId, @RequestBody OrderItem orderItem) {
         // Find the order by id, returns an Optional<Order>
        return orderRepository.findById(orderId)
                .map(order -> {
                    Product product = productRepository.findById(productId).orElseThrow();
                    // Complete the orderItem by attaching the full Order and Product objects
                    // JPA will extract their ids and save them as foreign keys (order_id, product_id) in the order_item table                    
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    
                    // Add the fully built orderItem to the order's list in memory
                    // This is required so JPA can find the orderItem when save() is called
                    order.getOrderItems().add(orderItem);
                    
                    // Triggers CascadeType.ALL which scans the orderItems list, detects the new orderItem,
                    // and automatically runs an INSERT into the order_item table with all its values
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
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
