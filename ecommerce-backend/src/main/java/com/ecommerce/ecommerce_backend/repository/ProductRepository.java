package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.Product;
// Is a Spring interface that comes with database operations already written
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring reads this method name and automatically generates:
    // SELECT * FROM product WHERE LOWER(name) LIKE LOWER('%query%')
    List<Product> findByNameContainingIgnoreCase(String query);
}