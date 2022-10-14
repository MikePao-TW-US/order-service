package com.multicart.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotCreatedException extends Exception{

    public ResourceNotCreatedException(String message){
        super(message);
    }
}
