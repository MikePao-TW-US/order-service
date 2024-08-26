package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.entity.Order;
import com.example.orderservice.orderservice.entity.OrderItem;
import com.example.orderservice.orderservice.models.requestModels.OrderRequestModel;
import com.example.orderservice.orderservice.repository.OrderItemRepository;
import com.example.orderservice.orderservice.repository.OrderRepository;
import com.example.orderservice.orderservice.service.interfaces.OrderService;
import com.example.orderservice.orderservice.util.Const;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;
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
//            logger.error("Error creating order : " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteOrder(Integer orderId) {orderRepository.deleteByOrderId(orderId);}
}
