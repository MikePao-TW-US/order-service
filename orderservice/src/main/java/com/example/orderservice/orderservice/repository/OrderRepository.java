package com.example.orderservice.orderservice.repository;

import com.example.orderservice.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(Integer userId);

    void deleteByOrderId(Integer orderId);
}
