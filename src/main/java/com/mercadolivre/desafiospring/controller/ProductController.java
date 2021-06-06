package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.entity.Product;
import com.mercadolivre.desafiospring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> p = productService.getProducts();
        return ResponseEntity.ok().body(p);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product p = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
}
