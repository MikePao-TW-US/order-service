package com.multicart.api.service.interfaces;

import com.multicart.api.models.requestModels.OrderRequestModel;

public interface OrderService {

    public Boolean createOrder(OrderRequestModel requestModel);
}
