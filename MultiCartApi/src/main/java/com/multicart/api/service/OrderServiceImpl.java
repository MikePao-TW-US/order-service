package com.multicart.api.service;

import com.multicart.api.entities.Order;
import com.multicart.api.entities.OrderItem;
import com.multicart.api.models.requestModels.OrderRequestModel;
import com.multicart.api.repository.OrderItemRepository;
import com.multicart.api.repository.OrderRepository;
import com.multicart.api.service.interfaces.OrderService;
import com.multicart.api.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Boolean createOrder(OrderRequestModel requestModel) {
        try{
            Order newOrder = new Order(
                    null,
                    requestModel.getUserId(),
                    requestModel.getOrderTotal(),
                    requestModel.getShippingAddress(),
                    Const.OrderStatus.IN_TRANSIT,
                    new Date(),
                    new Date()
            );

            Order createdOrder = orderRepository.save(newOrder);
            if(createdOrder != null){
                requestModel.getProductItems().forEach(productItem -> {
                    OrderItem orderItem = new OrderItem(
                            createdOrder.getOrderId(),
                            productItem.getProductId(),
                            productItem.getQuantity(),
                            new Date(),
                            new Date()
                    );
                    orderItemRepository.save(orderItem);
                });
                return true;
            }
            return false;
        }catch (Exception e){
            logger.error("Error creating order : " + e.getMessage());
            return false;
        }
    }
}
