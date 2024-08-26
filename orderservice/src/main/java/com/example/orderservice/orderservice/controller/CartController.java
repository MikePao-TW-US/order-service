package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.models.requestModels.CartRequestModel;
import com.example.orderservice.orderservice.models.responseModels.ResponseModel;
import com.example.orderservice.orderservice.models.responseModels.CartProductResponseModel;
import com.example.orderservice.orderservice.service.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @PostMapping()
    public ResponseModel addToCart(@RequestBody CartRequestModel requestModel){
        Boolean status = cartService.addToCart(requestModel);
        if(status){
            return new ResponseModel<Integer>(null,"Product added to cart successfully", HttpStatus.OK.value());
        }
        return new ResponseModel<Integer>(null,"Error adding product to cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @GetMapping("{id}")
    public ResponseModel getCartProducts(@PathVariable("id") Integer userId){
        List<CartProductResponseModel> cartProducts = cartService.getCartProducts(userId);
        System.out.println("Hello "+cartProducts);
        return new ResponseModel(cartProducts,"",HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}/{pid}")
    public ResponseModel deleteFromCart(@PathVariable("id") Integer userId, @PathVariable("pid") Integer productId){
        Boolean status = cartService.deleteFromCart(userId, productId);
        if(status){
            return new ResponseModel(null,"Product removed from cart successfully", HttpStatus.OK.value());
        }
        return new ResponseModel(null,"Error removing product from cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @PutMapping()
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
