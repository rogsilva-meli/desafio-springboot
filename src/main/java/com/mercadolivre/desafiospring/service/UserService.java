package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.entity.Post;
import com.mercadolivre.desafiospring.entity.Seller;
import com.mercadolivre.desafiospring.entity.User;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private SellerRepository sellerRepository;


    public UserService(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;

    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IndexOutOfBoundsException("User "+id +" not found"));
        return user;
    }

    public Seller followSeller(Integer userId, Integer userIdToFollow){
        Seller seller = sellerRepository.findById(userIdToFollow).get();
        User user = userRepository.findById(userId).get();

        seller.users.add(user);
        return sellerRepository.save(seller);
    }

    public void unfollowSeller(Integer userId, Integer userIdToUnfollow){
        Seller seller = sellerRepository.findById(userIdToUnfollow).get();
        User user = userRepository.findById(userId).get();

        seller.users.remove(user);
        sellerRepository.save(seller);
    }

    // Buscar vendedor e classificar em ordem decrescente os seus posts
    public User getUsersAsc(Integer userId, String order){

        User users = userRepository.findById(userId).get();

        if(order.equals("name_asc")){
            System.out.println("Escolheu: NAME_ASC");
            users.setSellers(users.getSellers());
            return users;
        }else if (order.equals("name_desc")){
            System.out.println("Escolheu: NAME_DESC");
            users.setSellers(users.getSellers());
            return users;
        }else{
            System.out.println("NENHUMA");
            users.setSellers(users.getSellers());
            return users;
        }

    }




}
