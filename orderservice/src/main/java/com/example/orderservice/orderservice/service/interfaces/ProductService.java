package com.example.orderservice.orderservice.service.interfaces;

import com.example.orderservice.orderservice.entity.Product;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product product);

    List<Product> getAllProducts();

    Product findProduct(Integer productId);
}

