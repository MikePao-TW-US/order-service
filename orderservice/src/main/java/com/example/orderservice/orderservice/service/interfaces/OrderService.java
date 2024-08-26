package com.example.orderservice.orderservice.service.interfaces;

import com.example.orderservice.orderservice.entity.Order;
import com.example.orderservice.orderservice.entity.OrderItem;
import com.example.orderservice.orderservice.entity.OrderItemCompositeKey;
import com.example.orderservice.orderservice.models.requestModels.OrderRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderService {

    public Boolean createOrder(OrderRequestModel requestModel);

    List<Order> getUserOrders(Integer userId);

    void deleteOrder(Integer orderId);
}
