package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.entity.Product;
import com.example.orderservice.orderservice.repository.ProductRepository;
import com.example.orderservice.orderservice.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

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
        return productRepository.findById(productId).orElse(null);
    }
}
