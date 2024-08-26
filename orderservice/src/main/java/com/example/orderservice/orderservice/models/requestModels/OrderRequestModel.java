package com.example.orderservice.orderservice.models.requestModels;

import com.example.orderservice.orderservice.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestModel {

    private Integer userId;
    private List<OrderItem> productItems;
    private String shippingAddress;
    private Integer orderTotal;

}
