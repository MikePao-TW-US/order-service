package com.multicart.api.repository;

import com.multicart.api.entities.OrderItem;
import com.multicart.api.entities.OrderItemCompositeKey;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemCompositeKey> {
}
