package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Post;
import com.mercadolivre.desafiospring.domain.entity.Product;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.service.PostService;
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
    private PostService postService;

    public ProductController(ProductService productService, SellerService sellerService, PostService postService) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.postService = postService;
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

    // Exercício US 00010
    @PostMapping("/products/newpromopost")
    public ResponseEntity<PostDTOPromo> createPromoPost(@RequestBody Post post){

        Post p = postService.createPost(post);

        PostDTOPromo postPromoDTO = postService.entityForPostPromoDTO(p);

        return ResponseEntity.status(HttpStatus.OK).body(postPromoDTO);
    }

    @GetMapping("/products/{userId}/countPromo")
    public ResponseEntity<SellerDTO00011> getCountSellerByUser(@PathVariable Integer userId){
        SellerDTO00011 s = sellerService.countPromoProducts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping("/products/{userId}/list")
    public ResponseEntity<SellerDTO00012> getPromoProductsBySeller(@PathVariable Integer userId){

        Seller s = sellerService.getSellerById(userId);

        SellerDTO00012 sellerDTO00012 = sellerService.convertSellerToSellerDTO00012(s);

        return ResponseEntity.status(HttpStatus.OK).body(sellerDTO00012);
    }




}
