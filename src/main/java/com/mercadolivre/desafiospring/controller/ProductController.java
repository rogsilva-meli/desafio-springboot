package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.SellerDTO0009;
import com.mercadolivre.desafiospring.domain.dto.UserDTO0004;
import com.mercadolivre.desafiospring.domain.entity.Product;
import com.mercadolivre.desafiospring.service.ProductService;
import com.mercadolivre.desafiospring.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    private ProductService productService;
    private SellerService sellerService;

    public ProductController(ProductService productService, SellerService sellerService) {
        this.productService = productService;
        this.sellerService = sellerService;
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

    @GetMapping("/products/followed/{userId}")
    public ResponseEntity<SellerDTO0009> followedOrder(@PathVariable Integer userId, @RequestParam String order){
        SellerDTO0009 s = sellerService.getProductsSellersOrder(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }
}
