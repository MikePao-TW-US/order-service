package com.multicart.api.service.interfaces;

import com.multicart.api.models.requestModels.CartRequestModel;
import com.multicart.api.models.responseModels.CartProductResponseModel;

import java.util.List;

public interface CartService {
    public Boolean addToCart(CartRequestModel requestModel);

    public Boolean deleteFromCart(CartRequestModel requestModel);

    Boolean updateCartProduct(CartRequestModel requestModel);

    Boolean emptyCart(CartRequestModel requestModel);

    List<CartProductResponseModel> getCartProducts(CartRequestModel requestModel);
}
