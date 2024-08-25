package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.entity.User;
import com.example.orderservice.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.orderservice.repository.UserRepository;
import com.example.orderservice.orderservice.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        //encode password before saving in db.
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreated(new Date());
        System.out.println("user" + user);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User id: " + userId + " does not exist"));
    }

    @Override
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email);
    }
}
