package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Post;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.repository.ProductRepository;
import com.mercadolivre.desafiospring.repository.UserRepository;
import com.mercadolivre.desafiospring.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class PostController {

    private PostService postService;
    private UserRepository userRepository;
    private ProductRepository productRepository;


    public PostController(PostService postService, UserRepository userRepository, ProductRepository productRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;

    }

    @GetMapping("/products/posts")
    public ResponseEntity<List<Post>> getAll(){
        List<Post> list = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // Exercício US 0005
    @PostMapping("/products/newpost")
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post){
        Post p = postService.createPost(post);

        PostDTO postDTO = postService.entityForPostDTO(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

    // Exercício US 0006
    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<SellerDTOUS0006> getAllFollowed(@PathVariable Integer userId){

        SellerDTOUS0006 listPost = postService.getListPostSellerDTOUS0006(userId);

        return ResponseEntity.status(HttpStatus.OK).body(listPost);
    }

    // Exercício US 00010
    @PostMapping("/products/newpromopost")
    public ResponseEntity<PostPromoDTO> createPromoPost(@RequestBody Post post){

        Post p = postService.createPost(post);

        PostPromoDTO postPromoDTO = postService.entityForPostPromoDTO(p);

        return ResponseEntity.status(HttpStatus.OK).body(postPromoDTO);
    }
}










