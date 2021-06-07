package com.mercadolivre.desafiospring.service;


import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Category;
import com.mercadolivre.desafiospring.domain.entity.Post;
import com.mercadolivre.desafiospring.domain.entity.Product;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.repository.CategoryRepository;
import com.mercadolivre.desafiospring.repository.PostRepository;
import com.mercadolivre.desafiospring.repository.ProductRepository;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private ProductRepository productRepository;
    private SellerRepository sellerRepository;
    private CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, ProductRepository productRepository,
                       SellerRepository sellerRepository, CategoryRepository categoryRepository) {
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
    public PostDTO convertPostDTOToPostDTOUS0006(PostDTO postDTO){

        PostDTO pDTO = PostDTO.builder()
                .userId(sellerRepository.getById(postDTO.getUserId()).getId())
                .id_post(postDTO.getId_post())
                .date(postDTO.getDate())
                .detail(postDTO.getDetail())
                .category(categoryRepository.getById(postDTO.getCategory()).getId())
                .price(postDTO.getPrice())
                .build();

        return pDTO;
    }

    // Converter PostDTO em PostDTOUS0006
    public PostDTO0009 convertPostToPostDTOUS0009(Post post){

        PostDTO0009 pDTO0009 = PostDTO0009.builder()
                .date(post.getDate())
                .product(post.getProduct())
                .build();

        return pDTO0009;
    }

    public PostDTOPromo entityForPostPromoDTO(Post post){

        PostDTOPromo postDTOPromo = PostDTOPromo.builder()
                .userId(post.getSeller().getId())
                .id_post(post.getId())
                .date(post.getDate())
                .detail(post.getProduct())
                .category(post.getCategory().getId())
                .price(post.getPrice())
                .hasPromo(post.isHasPromo())
                .discount(post.getDiscount())
                .build();
        return postDTOPromo;
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
    public List<PostDTO> convertListPostDTOForListPostDTOUS0006(List<PostDTO> list){

        List<PostDTO> listPostDTOUS0006 = new ArrayList<>();
        for (PostDTO d : list) {
            PostDTO postDTOUS0006 = convertPostDTOToPostDTOUS0006(d);
            listPostDTOUS0006.add(postDTOUS0006);
        }
        return listPostDTOUS0006;
    }

    // Converter Set de PostDTO em Set de PostDTOUS0009
    public List<PostDTO0009> convertListPostForListPostDTO0009(List<Post> list){

        List<PostDTO0009> postDTO0009List = new ArrayList<>();
        for (Post d : list) {
            PostDTO0009 postDTO0009 = convertPostToPostDTOUS0009(d);
            postDTO0009List.add(postDTO0009);
        }
        return postDTO0009List;
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

    public SellerDTO0006 getListPostSellerDTOUS0006(Integer userId){

        Seller s = getPostByIdLastTwoWeeks(userId);

        List<PostDTO> postDTOS = convertEntitySellerForListPostDTO(userId);

        List<PostDTO> listPostDTOUS0006 = convertListPostDTOForListPostDTOUS0006(postDTOS);

        listPostDTOUS0006.sort(Comparator.comparing(PostDTO::getDate).reversed());

        SellerDTO0006 res = SellerDTO0006.builder()
                .userId(s.getId())
                .posts(listPostDTOUS0006)
                .build();

        return res;
    }

    // Converter Set de PostDTO em Set de PostDTOUS00012
    public List<PostDTOPromo00012> convertListPostForListPostDTOPromo00012(List<Post> list){

        List<PostDTOPromo00012> postDTOPromo00012List = new ArrayList<>();
        for (Post d : list) {
            PostDTOPromo00012 postDTOPromo00012 = convertPostPromoDTO00012(d);
            if(postDTOPromo00012.isHasPromo()) {
                postDTOPromo00012List.add(postDTOPromo00012);
            }
        }
        return postDTOPromo00012List;
    }

    // Converter Post em PostDTOUS00012
    public PostDTOPromo00012 convertPostPromoDTO00012(Post post){

        PostDTOPromo00012 promo00012 = PostDTOPromo00012.builder()
                .id_post(post.getId())
                .date(post.getDate())
                .detail(post.getProduct())
                .category(post.getCategory().getId())
                .price(post.getPrice())
                .hasPromo(post.isHasPromo())
                .discount(post.getDiscount())
                .build();

        return promo00012;
    }

    public Seller getListPromoProductsBySeller(Integer userId){

        Seller s = sellerRepository.findById(userId).get();

        convertListPostForListPostDTOPromo00012(s.getPosts());

        return s;
    }


}
