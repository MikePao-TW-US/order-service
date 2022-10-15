package com.multicart.api.service;

import com.multicart.api.entities.User;
import com.multicart.api.exceptions.ResourceNotFoundException;
import com.multicart.api.repository.UserRepository;
import com.multicart.api.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        //encode password before saving in db.
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email);
    }
}
