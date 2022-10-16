package com.multicart.api.service.interfaces;

import com.multicart.api.entities.Order;
import com.multicart.api.models.requestModels.OrderRequestModel;

import java.util.List;

public interface OrderService {

    public Boolean createOrder(OrderRequestModel requestModel);

    List<Order> getUserOrders(Integer userId);
}
