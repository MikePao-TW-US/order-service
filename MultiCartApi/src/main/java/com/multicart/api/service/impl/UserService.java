package com.multicart.api.service.impl;

import com.multicart.api.Exception.UserNotFoundException;
import com.multicart.api.entities.User;

public interface UserService {

    User saveUser(User user);

    User getUser(Integer userId) throws UserNotFoundException;
}
