package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.entity.Cart;
import com.example.orderservice.orderservice.entity.CartCompositeKey;
import com.example.orderservice.orderservice.entity.Product;
import com.example.orderservice.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.orderservice.models.requestModels.CartRequestModel;
import com.example.orderservice.orderservice.models.responseModels.CartProductResponseModel;
import com.example.orderservice.orderservice.repository.CartRepository;
import com.example.orderservice.orderservice.repository.ProductRepository;
import com.example.orderservice.orderservice.service.interfaces.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

//    Logger logger = LoggerFactory.getLogger(this.getClass());
    private CartRepository cartRepository;

    private ProductRepository productRepository;

    @Override
    public Boolean addToCart(CartRequestModel requestModel) {
        try{
            Cart cartProduct = new Cart(
                    requestModel.getProductId(),
                    requestModel.getUserId(),
//                    requestModel.getQuantity(),
                    1,
                    new Date(),
                    new Date()
            );
            cartRepository.save(cartProduct);
            return true;
        }catch (Exception e){
//            logger.error("Exception adding product to cart : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteFromCart(Integer userId, Integer productId) {
        try{
            cartRepository.deleteById(
                    new CartCompositeKey(productId, userId)
            );
            return true;
        }catch (Exception e){
//            logger.error("Exception deleting product from cart : " + e.getMessage());
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
//            logger.error("Exception updating product in cart : " + e.getMessage());
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean emptyCart(CartRequestModel requestModel) {
        try {
            cartRepository.deleteByUserId(requestModel.getUserId());
            return true;
        }catch (Exception e){
//            logger.error("Exception while making cart empty : " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<CartProductResponseModel> getCartProducts(Integer userId) {
        List<CartProductResponseModel> responseModelList = new ArrayList<>();
        List<Cart> cartProducts =  cartRepository.findByUserId(userId);
        cartProducts.forEach(cartProduct -> {
            CartProductResponseModel model = new CartProductResponseModel();
            model.setQuantity(cartProduct.getQuantity());
            Product product = productRepository.findById(cartProduct.getProductId())
                    .orElseThrow(() ->
                    new ResourceNotFoundException("Product:" + cartProduct.getProductId() + " does not exist"));;
            model.setProduct(product);
            responseModelList.add(model);
        });
        return responseModelList;
    }
}
