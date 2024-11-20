package com.wings.may24.controller;

import com.wings.may24.model.Product;
import com.wings.may24.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    private ResponseEntity<Product> postProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    private List<Product> getProducts(){
        return productService.getProducts();
    }
}
