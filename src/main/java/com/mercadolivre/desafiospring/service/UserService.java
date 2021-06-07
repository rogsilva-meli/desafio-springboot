package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.domain.dto.SellerDTO0004;
import com.mercadolivre.desafiospring.domain.dto.UserDTO;
import com.mercadolivre.desafiospring.domain.dto.UserDTO0003;
import com.mercadolivre.desafiospring.domain.dto.UserDTO0004;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.domain.entity.User;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private SellerService sellerService;


    public UserService(UserRepository userRepository, SellerRepository sellerRepository, SellerService sellerService) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.sellerService = sellerService;
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


    public UserDTO0004 convertUserToUserDTOUS0004(User user){
        UserDTO0004 userDTOUS0004 = UserDTO0004.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .followed(sellerService.convertSellerToSellerDTO0004(user.getSellers()))
                .build();
        return userDTOUS0004;
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
    public UserDTO0004 getUsersAsc(Integer userId, String order){

        User user = userRepository.findById(userId).get();
        UserDTO0004 userDTO0004 = convertUserToUserDTOUS0004(user);

        if(order.equals("name_asc")) {
            userDTO0004.getFollowed().sort(Comparator.comparing(SellerDTO0004::getUserName));
            return userDTO0004;
        } else if(order.equals("name_desc")){
            userDTO0004.getFollowed().sort(Comparator.comparing(SellerDTO0004::getUserName).reversed());
            return userDTO0004;
        } else {
            return userDTO0004;
        }
    }




}
