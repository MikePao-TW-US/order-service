package com.example.orderservice.orderservice.repository;

import com.example.orderservice.orderservice.entity.Cart;
import com.example.orderservice.orderservice.entity.CartCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartCompositeKey> {
    public void deleteByUserId(Integer userId);

    public List<Cart> findByUserId(Integer userId);
}
