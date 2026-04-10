package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// This class handles HTTP requests and automatically converts return values to JSON"
@RestController 
// All routes in this class start with /api/products
@RequestMapping("/api/products")
// Allows your frontend to call this API without being blocked by the browser's security
@CrossOrigin(origins = "*") 
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	// Listens for GET /api/products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
	return ResponseEntity.ok(productRepository.findAll());
	}

	// Listens for GET /api/products/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable long id) {
		return productRepository.findById(id)
				.map(ResponseEntity::ok) // If the product was found, wrap it in a `200 OK` response
				.orElse(ResponseEntity.notFound().build()); // If nothing was found, return a `404 Not Found`
	}

	// Listens for GET /api/products/search?query=
	@GetMapping("/search")
	public ResponseEntity<List<Product>> getRequestedProduct(@RequestParam String query) {
		// Search for products whose name contains the query string (case insensitive)
    	// Returns 200 with a list of matching products, or an empty array if none found
		return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(query));
	}
}
