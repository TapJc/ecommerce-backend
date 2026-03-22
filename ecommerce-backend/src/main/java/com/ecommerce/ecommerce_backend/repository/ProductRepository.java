package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.Product;
// Is a Spring interface that comes with database operations already written
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}