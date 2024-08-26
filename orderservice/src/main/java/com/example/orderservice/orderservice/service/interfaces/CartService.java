package com.example.orderservice.orderservice.service.interfaces;

import com.example.orderservice.orderservice.models.requestModels.CartRequestModel;
import com.example.orderservice.orderservice.models.responseModels.CartProductResponseModel;

import java.util.List;

public interface CartService {
    public Boolean addToCart(CartRequestModel requestModel);

    public Boolean deleteFromCart(Integer userId, Integer productId);

    Boolean updateCartProduct(CartRequestModel requestModel);

    Boolean emptyCart(CartRequestModel requestModel);

    List<CartProductResponseModel> getCartProducts(Integer userId);
}
