package com.multicart.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCompositeKey implements Serializable {
    private Integer productId;
    private Integer userId;
}
