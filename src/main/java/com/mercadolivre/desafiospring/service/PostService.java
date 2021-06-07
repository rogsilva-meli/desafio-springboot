package com.mercadolivre.desafiospring.service;


import com.mercadolivre.desafiospring.dto.PostDTO;
import com.mercadolivre.desafiospring.dto.PostDTOUS0006;
import com.mercadolivre.desafiospring.dto.PostPromoDTO;
import com.mercadolivre.desafiospring.dto.PostPromoDTOUS00010;
import com.mercadolivre.desafiospring.entity.*;
import com.mercadolivre.desafiospring.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class PostService {

    private PostRepository postRepository;
    private ProductRepository productRepository;
    private SellerRepository sellerRepository;
    private CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, ProductRepository productRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Post> getAllPosts(){

        return postRepository.findAll();
    }

    // Converter entidade para PostDTO
    public PostDTO entityForPostDTO(Post post){

        PostDTO p = PostDTO.builder()
                .userId(post.getSeller().getId())
                .id_post(post.getId())
                .date(post.getDate())
                .detail(post.getProduct())
                .category(post.getCategory().getId())
                .price(post.getPrice())
                .build();
        return p;
    }

    // Converter PostDTO em PostDTOUS0006
    public PostDTOUS0006 convertPostDTOToPostDTOUS0006(PostDTO postDTO){

        PostDTOUS0006 p = PostDTOUS0006.builder()
                .userId(sellerRepository.getById(postDTO.getUserId()).getId())
                .id_post(postDTO.getId_post())
                .date(postDTO.getDate())
                .detail(postDTO.getDetail())
                .category(categoryRepository.getById(postDTO.getCategory()).getId())
                .price(postDTO.getPrice())
                .build();

        return p;
    }

    public PostPromoDTO entityForPostPromoDTO(Post post){

        PostPromoDTO p = PostPromoDTO.builder()
                .userId(post.getSeller().getId())
                .id_post(post.getId())
                .date(post.getDate())
                .detail(post.getProduct())
                .category(post.getCategory().getId())
                .price(post.getPrice())
                .hasPromo(post.isHasPromo())
                .discount(post.getDiscount())
                .build();
        return p;
    }


    // Criar um post
    public Post createPost(Post post){

        Seller s = sellerRepository.findById(post.getSeller().getId()).get();
        Product p = productRepository.findById(post.getProduct().getProduct_id()).get();
        Category c = categoryRepository.findById(post.getCategory().getId()).get();
        post.setSeller(s);
        post.setProduct(p);
        post.setCategory(c);

        if(!post.isHasPromo()){
            post.setHasPromo(false);
        }


        return postRepository.save(post);
    }

    // Converter PostDTO em Set de PostDTO
    public Set<PostDTO> convertEntitySellerForListPostDTO(Integer userId){

        Seller s = getPostById(userId);

        Set<PostDTO> listPostDTO = new HashSet<>();
        for (Post c : s.getPosts()) {
            PostDTO postDTO = entityForPostDTO(c);
            listPostDTO.add(postDTO);
        }
        return listPostDTO;
    }

    // Converter Set de PostDTO em Set de PostDTOUS0006
    public Set<PostDTOUS0006> convertListPostDTOForListPostDTOUS0006(Set<PostDTO> list){

        Set<PostDTOUS0006> listPostDTOUS0006 = new HashSet<>();
        for (PostDTO d : list) {
            PostDTOUS0006 postDTOUS0006 = convertPostDTOToPostDTOUS0006(d);
            listPostDTOUS0006.add(postDTOUS0006);
        }
        return listPostDTOUS0006;
    }

    // Converter PostDTO em Set de PostDTO
    public Set<PostPromoDTO> convertEntitySellerForListPostPromoDTO(Integer userId){

        Seller s = getPostById(userId);

        Set<PostPromoDTO> listPostPromoDTO = new HashSet<>();
        for (Post c : s.getPosts()) {
            PostPromoDTO postPromoDTO = entityForPostPromoDTO(c);
            listPostPromoDTO.add(postPromoDTO);
        }
        return listPostPromoDTO;
    }


    // FALTA ARRUMAR A ORDENAÇÃO
    // US 0006 Buscar vendedor e classificar em ordem decrescente os seus posts
    public Seller getPostById(Integer userId){

        LocalDate today = LocalDate.now();
        LocalDate twoWeeks = today.minusDays(14);

        Seller s = sellerRepository.findById(userId).get();

        Set<Post> listSet = new HashSet<>();
        for (Post p : s.getPosts()) {
            if(p.getDate().isAfter(twoWeeks)){
                listSet.add(p);
            }
        }

        s.setPosts(listSet);
        return s;
    }


}
