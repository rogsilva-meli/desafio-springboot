package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.dto.PostDTO;
import com.mercadolivre.desafiospring.dto.PostDTOUS0006;
import com.mercadolivre.desafiospring.dto.SellerDTOUS0006;
import com.mercadolivre.desafiospring.entity.Post;
import com.mercadolivre.desafiospring.entity.Seller;
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

        Seller s = postService.getPostById(userId);

        Set<PostDTO> postDTOS = postService.convertEntitySellerForListPostDTO(userId);

        List<PostDTO> list = new ArrayList<>();
        list.addAll(postDTOS);
        for (PostDTO p : list) {
            System.out.println("Data: "+p.getDate());
        }
        Collections.reverse(list);
        Set<PostDTO> listRev = new HashSet<>();
        listRev.addAll(list);

        Set<PostDTOUS0006> listPostDTOUS0006 = postService.convertListPostDTOForListPostDTOUS0006(listRev);

        SellerDTOUS0006 res = SellerDTOUS0006.builder().userId(s.getId())
                .posts(listPostDTOUS0006)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}










