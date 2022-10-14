package com.multicart.api.controller;

import com.multicart.api.Exception.UserNotFoundException;
import com.multicart.api.authentication.JwtUtility;
import com.multicart.api.entities.User;
import com.multicart.api.models.requestModels.JwtRequestModel;
import com.multicart.api.models.responseModels.JwtResponseModel;
import com.multicart.api.service.UserDetailsServiceImpl;
import com.multicart.api.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtility jwtUtility;

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
            return new JwtResponseModel(null,"Bad Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestModel.getUsername());
        final String jwtToken = jwtUtility.generateToken(userDetails);
        return new JwtResponseModel(jwtToken,"Success");
    }

    @GetMapping("/info/{userId}")
    public User getUserInfo(@PathVariable Integer userId) throws UserNotFoundException {
        User user = userService.getUser(userId);
        if(user == null){
            throw new UserNotFoundException("User does not exists");
        }
        return user;
    }
}
