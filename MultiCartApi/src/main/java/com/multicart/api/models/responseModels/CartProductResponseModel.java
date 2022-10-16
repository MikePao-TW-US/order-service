package com.multicart.api.models.responseModels;

import com.multicart.api.entities.Product;
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
