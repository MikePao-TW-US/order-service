package com.example.orderservice.orderservice.exceptions;

public class ResourceNotCreatedException extends RuntimeException{

    public ResourceNotCreatedException(String message){
        super(message);
    }
}
