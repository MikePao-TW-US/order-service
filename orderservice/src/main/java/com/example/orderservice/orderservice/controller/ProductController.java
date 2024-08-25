package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.entity.Product;
import com.example.orderservice.orderservice.exceptions.ResourceNotCreatedException;
import com.example.orderservice.orderservice.exceptions.ResourceNotFoundException;
import com.example.orderservice.orderservice.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody Product product) throws ResourceNotCreatedException {
        Product createdProduct = productService.addNewProduct(product);
        if(createdProduct == null){
            throw new ResourceNotCreatedException("Error creating product.");
        }
        return createdProduct;
    }

    @GetMapping()
    public List<Product> getAllProducts() throws ResourceNotFoundException {
//        System.out.println("getAllProducts");
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

