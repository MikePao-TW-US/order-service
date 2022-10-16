package com.multicart.api.repository;

import com.multicart.api.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findByUserId(Integer userId);
}
