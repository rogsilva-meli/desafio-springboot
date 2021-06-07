package com.mercadolivre.desafiospring.service;


import com.mercadolivre.desafiospring.domain.dto.PostDTO;
import com.mercadolivre.desafiospring.domain.dto.PostDTOUS0006;
import com.mercadolivre.desafiospring.domain.dto.PostPromoDTO;
import com.mercadolivre.desafiospring.domain.dto.SellerDTOUS0006;
import com.mercadolivre.desafiospring.domain.entity.*;
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
    public List<PostDTO> convertEntitySellerForListPostDTO(Integer userId){

        Seller s = getPostById(userId);

        List<PostDTO> listPostDTO = new ArrayList<>();
        for (Post c : s.getPosts()) {
            PostDTO postDTO = entityForPostDTO(c);
            listPostDTO.add(postDTO);
        }
        return listPostDTO;
    }

    // Converter Set de PostDTO em Set de PostDTOUS0006
    public List<PostDTOUS0006> convertListPostDTOForListPostDTOUS0006(List<PostDTO> list){

        List<PostDTOUS0006> listPostDTOUS0006 = new ArrayList<>();
        for (PostDTO d : list) {
            PostDTOUS0006 postDTOUS0006 = convertPostDTOToPostDTOUS0006(d);
            listPostDTOUS0006.add(postDTOUS0006);
        }
        return listPostDTOUS0006;
    }

    // Converter PostDTO em Set de PostDTO
    public List<PostPromoDTO> convertEntitySellerForListPostPromoDTO(Integer userId){

        Seller s = getPostById(userId);

        List<PostPromoDTO> listPostPromoDTO = new ArrayList<>();
        for (Post c : s.getPosts()) {
            PostPromoDTO postPromoDTO = entityForPostPromoDTO(c);
            listPostPromoDTO.add(postPromoDTO);
        }
        return listPostPromoDTO;
    }


    // US 0006 Buscar vendedor e classificar em ordem decrescente os seus posts
    public Seller getPostById(Integer userId){

        Seller s = sellerRepository.findById(userId).get();

        return s;
    }

    // US 0006 Buscar vendedor e classificar em ordem decrescente os seus posts
    public Seller getPostByIdLastTwoWeeks(Integer userId){

        LocalDate today = LocalDate.now();
        LocalDate twoWeeks = today.minusWeeks(2);

        Seller s = sellerRepository.findById(userId).get();

        List<Post> list = new ArrayList<>();
        for (Post p : s.getPosts()) {
            if(p.getDate().isAfter(twoWeeks)){
                list.add(p);
            }
        }

        s.setPosts(list);
        return s;
    }

    public SellerDTOUS0006 getListPostSellerDTOUS0006(Integer userId){

        Seller s = getPostByIdLastTwoWeeks(userId);

        List<PostDTO> postDTOS = convertEntitySellerForListPostDTO(userId);

        List<PostDTO> list = new ArrayList<>();
        list.addAll(postDTOS);

        List<PostDTOUS0006> listPostDTOUS0006 = convertListPostDTOForListPostDTOUS0006(list);

        listPostDTOUS0006.sort(Comparator.comparing(PostDTOUS0006::getDate).reversed());

        SellerDTOUS0006 res = SellerDTOUS0006.builder()
                .userId(s.getId())
                .posts(listPostDTOUS0006)
                .build();

        return res;
    }


}
