package com.multicart.api.controller;

import com.multicart.api.models.requestModels.OrderRequestModel;
import com.multicart.api.models.responseModels.ResponseModel;
import com.multicart.api.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseModel placeOrder(@RequestBody OrderRequestModel requestModel){
        Boolean status = orderService.createOrder(requestModel);
        if(status){
            return new ResponseModel(null,"Order created successfully", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error creating order", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
