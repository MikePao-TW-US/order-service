package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.entity.Order;
import com.example.orderservice.orderservice.models.requestModels.OrderRequestModel;
import com.example.orderservice.orderservice.service.interfaces.OrderService;
import com.example.orderservice.orderservice.models.responseModels.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseModel placeOrder(@RequestBody OrderRequestModel requestModel){
        Boolean status = orderService.createOrder(requestModel);
        if(status){
            return new ResponseModel(null,"Order created successfully", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error creating order", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @GetMapping("{id}")
    public List<Order> getAllOrders(@PathVariable("id") Integer userId){
        return orderService.getUserOrders(userId);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Integer orderId){
        orderService.deleteOrder(orderId);
    }
}
