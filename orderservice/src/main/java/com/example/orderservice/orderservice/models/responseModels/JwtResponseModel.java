package com.example.orderservice.orderservice.models.responseModels;

import com.example.orderservice.orderservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseModel {
    private User user;
    private String jwtToken;
    private String message;
}
