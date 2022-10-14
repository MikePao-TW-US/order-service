package com.multicart.api.service.interfaces;

import com.multicart.api.exceptions.ResourceNotFoundException;
import com.multicart.api.entities.User;

public interface UserService {

    User saveUser(User user);

    User getUser(Integer userId) throws ResourceNotFoundException;
}
