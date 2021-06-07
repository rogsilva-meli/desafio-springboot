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

        PostDTO postDTO = PostDTO.builder()
                //.userId(p.getSeller().getId())
                .id_post(p.getId())
                .date(p.getDate())
                .detail(p.getProduct())
                .category(p.getCategory().getId())
                .price(p.getPrice())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

    // Exercício US 0006
    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<SellerDTO0006> getAllFollowed(@PathVariable Integer userId){

        SellerDTO0006 posts = postService.getListPostSellerDTOUS0006(userId);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // Exercício US 00010
    @PostMapping("/products/newpromopost")
    public ResponseEntity<PostDTO> createPromoPost(@RequestBody Post post){

        Post p = postService.createPost(post);

        PostDTO postPromoDTO = postService.entityForPostPromoDTO(p);

        return ResponseEntity.status(HttpStatus.OK).body(postPromoDTO);
    }
}










