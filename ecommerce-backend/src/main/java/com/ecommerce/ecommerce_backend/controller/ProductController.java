package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // allow frontend to access
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  // Get all products
  @GetMapping
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  // Get product by ID
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
