package com.multicart.api.models.requestModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multicart.api.models.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestModel {

    private Integer userId;
    private List<ProductItem> productItems;
    private String shippingAddress;
    private Integer orderTotal;

}

