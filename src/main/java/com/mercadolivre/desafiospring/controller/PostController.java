package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Post;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.repository.ProductRepository;
import com.mercadolivre.desafiospring.repository.UserRepository;
import com.mercadolivre.desafiospring.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/products/posts")
    @ApiOperation(value= "Retorna uma lista de publicações")
    public ResponseEntity<List<Post>> getAll(){
        List<Post> list = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // Exercício US 0005
    @PostMapping("/products/newpost")
    @ApiOperation(value= "Cria uma nova publicação")
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post){
        Post p = postService.createPost(post);

        PostDTO postDTO = PostDTO.builder()
                .userId(p.getSeller().getId())
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
    @ApiOperation(value= "Retorna uma lista descrescente das publicações feitas pelos vendedores que um usuário segue nas últimas duas semanas")
    public ResponseEntity<SellerDTO0006> getAllFollowed(@PathVariable Integer userId){

        SellerDTO0006 posts = postService.getListPostSellerDTOUS0006(userId);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }


}










