package com.multicart.api.models.responseModels;

import com.multicart.api.entities.User;
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
