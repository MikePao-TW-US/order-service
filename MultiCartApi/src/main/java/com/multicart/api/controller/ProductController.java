package com.multicart.api.controller;

import com.multicart.api.entities.Product;
import com.multicart.api.exceptions.ResourceNotCreatedException;
import com.multicart.api.exceptions.ResourceNotFoundException;
import com.multicart.api.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) throws ResourceNotCreatedException {
        Product createdProduct = productService.addNewProduct(product);
        if(createdProduct == null){
            throw new ResourceNotCreatedException("Error creating product.");
        }
        return createdProduct;
    }

    @GetMapping("/getall")
    public List<Product> getAllProducts() throws ResourceNotFoundException {
        List<Product> products = productService.getAllProducts();
        if(products == null || products.isEmpty()){
            throw new ResourceNotFoundException("No Products Available");
        }
        return products;
    }

    @GetMapping("/info/{productId}")
    public Product getProductInfo(@PathVariable Integer productId) throws ResourceNotFoundException{
        Product product = productService.findProduct(productId);
        if(product == null){
            throw new ResourceNotFoundException("Product not exists");
        }
        return product;
    }
}
