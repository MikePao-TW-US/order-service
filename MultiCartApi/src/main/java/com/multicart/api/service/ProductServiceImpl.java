package com.multicart.api.service;

import com.multicart.api.entities.Product;
import com.multicart.api.repository.ProductRepository;
import com.multicart.api.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addNewProduct(Product product) {
        product.setCreated(new Date());
        product.setModified(new Date());
        product.setDeleted(false);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findProduct(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            return productOptional.get();
        }
        return null;
    }
}
