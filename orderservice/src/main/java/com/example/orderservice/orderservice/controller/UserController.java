package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.authentication.JwtUtility;
import com.example.orderservice.orderservice.entity.User;
import com.example.orderservice.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.orderservice.models.requestModels.JwtRequestModel;
import com.example.orderservice.orderservice.models.responseModels.JwtResponseModel;
import com.example.orderservice.orderservice.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserDetailsService userDetailsService;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtility jwtUtility;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public JwtResponseModel authenticateUser(@RequestBody JwtRequestModel requestModel) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestModel.getUsername(),
                            requestModel.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            return new JwtResponseModel(null,null,"Bad Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestModel.getUsername());
        final String jwtToken = jwtUtility.generateToken(userDetails);
        final User user = userService.getUserByEmail(userDetails.getUsername());
        return new JwtResponseModel(user,jwtToken,"Success");
    }

    @GetMapping("/info/{userId}")
    public User getUserInfo(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {

        User user = userService.getUser(userId);
        if(user == null){
            throw new ResourceNotFoundException("User does not exists");
        }
        return user;
    }
}
