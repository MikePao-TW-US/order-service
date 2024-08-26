package com.example.orderservice.orderservice.repository;

import com.example.orderservice.orderservice.entity.OrderItem;
import com.example.orderservice.orderservice.entity.OrderItemCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemCompositeKey> {
}
