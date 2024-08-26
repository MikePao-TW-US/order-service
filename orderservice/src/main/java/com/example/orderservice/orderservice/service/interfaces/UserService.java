package com.example.orderservice.orderservice.service.interfaces;

import com.example.orderservice.orderservice.entity.User;
import com.example.orderservice.orderservice.exceptions.ResourceNotFoundException;

public interface UserService {

    User saveUser(User user);

    User getUser(Integer userId) throws ResourceNotFoundException;

    User getUserByEmail(String email) throws ResourceNotFoundException;
}
