package com.multicart.api.service.interfaces;

import com.multicart.api.entities.Product;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product product);

    List<Product> getAllProducts();

    Product findProduct(Integer productId);
}
