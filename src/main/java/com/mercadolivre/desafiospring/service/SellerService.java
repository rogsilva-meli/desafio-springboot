package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.domain.entity.User;
import com.mercadolivre.desafiospring.exception.error.NotFoundException;
import com.mercadolivre.desafiospring.exception.error.UserSellerValidateRequestException;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SellerService {

    private SellerRepository sellerRepository;
    private PostService postService;

    public SellerService(SellerRepository sellerRepository, PostService postService) {
        this.sellerRepository = sellerRepository;
        this.postService = postService;
    }

    public List<Seller> getAllSellers() {
        List<Seller> all = sellerRepository.findAll();
        Collections.reverse(all);
        return all;
    }

    public Seller createSeller(SellerDTORegister seller) {
        if(seller.getSellerName().isEmpty()){
            throw new UserSellerValidateRequestException("Sellername field can't be empty");
        }
        Seller request = new Seller();
        request.setSellerName(seller.getSellerName());
        return sellerRepository.save(request);
    }

    public SellerDTO0002 countUsers(Integer id){
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User "+id +" not found"));

        int i=(int)seller.getUsers().stream().count();

        SellerDTO0002 sellerDTO = SellerDTO0002.builder()
                .userId(seller.getId())
                .userName(seller.getSellerName())
                .followers_count(i)
                .build();
        return  sellerDTO;
    }

    public Seller getSellerById(Integer id){
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller "+id +" not found"));
        return seller;
    }

    public SellerDTO0003 convertSellerToSellerDTOUS0003 (Seller s){
        SellerDTO0003 sellerDTO0003 = SellerDTO0003.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers(convertUserToUserUserDTOUS0003(s.getUsers()))
                .build();

        return sellerDTO0003;
    }

    public SellerDTO0009 convertSellerToSellerDTO0009 (Seller s){
        SellerDTO0009 sellerDTO0009 = SellerDTO0009.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .products(postService.convertListPostForListPostDTO0009(s.getPosts()))
                .build();

        return sellerDTO0009;
    }

    public SellerDTO0004 convertSellerToSellerDTOUS0004 (Seller s){
        SellerDTO0004 sellerDTO0004 = SellerDTO0004.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .build();

        return sellerDTO0004;
    }

    public List<UserDTO0003> convertUserToUserUserDTOUS0003 (List<User> listUser){
        List<UserDTO0003> userDTO0003 = new ArrayList<>();

        for(User u: listUser){
            UserDTO0003 build = UserDTO0003.builder()
                    .userId(u.getId())
                    .userName(u.getUserName())
                    .build();
            userDTO0003.add(build);
        }

        return userDTO0003;
    }

    public List<SellerDTO0004> convertSellerToSellerDTO0004(List<Seller> listSeller){
        List<SellerDTO0004> sellers = new ArrayList<>();

        for(Seller s: listSeller){
            SellerDTO0004 build = SellerDTO0004.builder()
                    .userId(s.getId())
                    .userName(s.getSellerName())
                    .build();
            sellers.add(build);
        }
        return sellers;
    }

    public SellerDTO00012 convertSellerToSellerDTO00012(Seller s){

        SellerDTO00012 sellerDTO00012 = SellerDTO00012.builder()
                    .userId(s.getId())
                    .posts(postService.convertListPostForListPostDTOPromo00012(s.getPosts()))
                    .build();
        return sellerDTO00012;
    }

    // Buscar vendedor e classificar em ordem decrescente os seus posts
    public SellerDTO0003 getSellersAsc(Integer userId, String order){

        Seller seller = sellerRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User "+userId +" not found"));
        SellerDTO0003 sellerDTO0003 = convertSellerToSellerDTOUS0003(seller);

        if(order.equals("name_asc")) {
            sellerDTO0003.getFollowers().sort(Comparator.comparing(UserDTO0003::getUserName));
            return sellerDTO0003;
        } else if(order.equals("name_desc")){
            sellerDTO0003.getFollowers().sort(Comparator.comparing(UserDTO0003::getUserName).reversed());
            return sellerDTO0003;
        } else {
            return sellerDTO0003;
        }
    }



    // Buscar vendedor e classificar em ordem decrescente os seus posts
    public SellerDTO0009 getProductsSellersOrder(Integer userId, String order){

        Seller seller = sellerRepository.findById(userId).orElseThrow(() -> new NotFoundException("User "+userId +" not found"));
        SellerDTO0009 sellerDTO0009 = convertSellerToSellerDTO0009(seller);

        if(order.equals("name_asc")) {
            sellerDTO0009.getProducts().sort(Comparator.comparing(PostDTO0009::getDate));
            return sellerDTO0009;
        } else if(order.equals("name_desc")){
            sellerDTO0009.getProducts().sort(Comparator.comparing(PostDTO0009::getDate).reversed());
            return sellerDTO0009;
        } else {
            return sellerDTO0009;
        }
    }

    public SellerDTO00011 countPromoProducts(Integer id){
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new NotFoundException("Seller "+id +" not found"));

        int i=(int)seller.getPosts().stream().filter(p -> p.isHasPromo()).count();

        SellerDTO00011 sellerDTO00011 = SellerDTO00011.builder()
                .userId(seller.getId())
                .userName(seller.getSellerName())
                .promoproducts_count(i)
                .build();
        return  sellerDTO00011;
    }




}
