package com.multicart.api.controller;

import com.multicart.api.models.requestModels.CartRequestModel;
import com.multicart.api.models.responseModels.ResponseModel;
import com.multicart.api.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseModel addToCart(@RequestBody CartRequestModel requestModel){
        Boolean status = cartService.addToCart(requestModel);
        if(status){
            return new ResponseModel<Integer>(null,"Product added to cart successfully", HttpStatus.OK.value());
        }
        return new ResponseModel<Integer>(null,"Error adding product to cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @PostMapping("/delete")
    public ResponseModel deleteFromCart(@RequestBody CartRequestModel requestModel){
        Boolean status = cartService.deleteFromCart(requestModel);
        if(status){
            return new ResponseModel(null,"Product removed from cart successfully", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error removing product from cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @PostMapping("/update")
    public ResponseModel updateCart(@RequestBody CartRequestModel requestModel){
        Boolean status = cartService.updateCartProduct(requestModel);
        if(status){
            return new ResponseModel(null,"Product updated in cart successfully", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error updating cart product.", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @PostMapping("/empty")
    public ResponseModel emptyCart(@RequestBody CartRequestModel requestModel){
        Boolean status = cartService.emptyCart(requestModel);
        if(status){
            return new ResponseModel(null,"Cart is empty now.", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error making the cart empty.", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
