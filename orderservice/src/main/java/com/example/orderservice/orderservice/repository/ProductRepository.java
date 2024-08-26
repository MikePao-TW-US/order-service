package com.example.orderservice.orderservice.repository;

import com.example.orderservice.orderservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
