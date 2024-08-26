package com.example.orderservice.orderservice.models.responseModels;
import com.example.orderservice.orderservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponseModel {
    Product product;
    Integer quantity;
}
