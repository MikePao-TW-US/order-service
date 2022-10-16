package com.multicart.api.service;

import com.multicart.api.entities.Cart;
import com.multicart.api.entities.CartCompositeKey;
import com.multicart.api.entities.Product;
import com.multicart.api.exceptions.ResourceNotFoundException;
import com.multicart.api.models.requestModels.CartRequestModel;
import com.multicart.api.models.responseModels.CartProductResponseModel;
import com.multicart.api.repository.CartRepository;
import com.multicart.api.repository.ProductRepository;
import com.multicart.api.service.interfaces.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CartServiceImpl implements CartService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Boolean addToCart(CartRequestModel requestModel) {
       try{
           Cart cartProduct = new Cart(
                   requestModel.getProductId(),
                   requestModel.getUserId(),
                   1,
                   new Date(),
                   new Date()
           );
           cartRepository.save(cartProduct);
           return true;
       }catch (Exception e){
           logger.error("Exception adding product to cart : " + e.getMessage());
           return false;
       }
    }

    @Override
    public Boolean deleteFromCart(CartRequestModel requestModel) {
        try{
            cartRepository.deleteById(
                    new CartCompositeKey(requestModel.getProductId(), requestModel.getUserId())
            );
            return true;
        }catch (Exception e){
            logger.error("Exception deleting product from cart : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateCartProduct(CartRequestModel requestModel) {
        try{
            Optional<Cart> cartOptional = cartRepository.findById(
                    new CartCompositeKey(
                            requestModel.getProductId(),
                            requestModel.getUserId()
                    )
            );
            if(!cartOptional.isPresent()){
                throw new ResourceNotFoundException("Product does not exist in cart");
            }
            Cart cartProduct = cartOptional.get();
            cartProduct.setQuantity(requestModel.getQuantity());
            cartProduct.setModified(new Date());
            cartRepository.save(cartProduct);
            return true;
        }catch (Exception e){
            logger.error("Exception updating product in cart : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean emptyCart(CartRequestModel requestModel) {
        try {
            cartRepository.deleteByUserId(requestModel.getUserId());
            return true;
        }catch (Exception e){
            logger.error("Exception while making cart empty : " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<CartProductResponseModel> getCartProducts(CartRequestModel requestModel) {
        List<CartProductResponseModel> responseModelList = new ArrayList<>();
        List<Cart> cartProducts =  cartRepository.findByUserId(requestModel.getUserId());
        cartProducts.forEach(cartProduct -> {
            CartProductResponseModel model = new CartProductResponseModel();
            model.setQuantity(cartProduct.getQuantity());
            Product product = productRepository.findById(cartProduct.getProductId()).get();
            model.setProduct(product);
            responseModelList.add(model);
        });
        return responseModelList;
    }
}
