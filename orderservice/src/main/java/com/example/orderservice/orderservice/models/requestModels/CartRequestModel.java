package com.example.orderservice.orderservice.models.requestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestModel {

    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
