package com.multicart.api.service.interfaces;

import com.multicart.api.models.requestModels.CartRequestModel;

public interface CartService {
    public Boolean addToCart(CartRequestModel requestModel);

    public Boolean deleteFromCart(CartRequestModel requestModel);

    Boolean updateCartProduct(CartRequestModel requestModel);

    Boolean emptyCart(CartRequestModel requestModel);
}
