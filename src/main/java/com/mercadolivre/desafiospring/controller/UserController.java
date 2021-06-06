package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.dto.SellerDTO;
import com.mercadolivre.desafiospring.dto.SellerDTOUS0003;
import com.mercadolivre.desafiospring.dto.UserDTOUS0004;
import com.mercadolivre.desafiospring.entity.Seller;
import com.mercadolivre.desafiospring.entity.User;
import com.mercadolivre.desafiospring.service.SellerService;
import com.mercadolivre.desafiospring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private SellerService sellerService;

    public UserController(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User u = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);

    }

    // Exercício US 0001
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Seller> addSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToFollow
    ) {
        Seller s = userService.followUser(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    // Exercício US 0002
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<SellerDTO> getCountSellerByUser(@PathVariable Integer userId){
        Seller s = sellerService.countUsers(userId);
        SellerDTO sellerDTO = SellerDTO.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers_count(s.getFollowers_count())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(sellerDTO);
    }

    // Exercício US 0003
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<SellerDTOUS0003> getSellerById(@PathVariable Integer userId){
        Seller s = sellerService.getSellerById(userId);
        SellerDTOUS0003 sellerDTOUS0003 = SellerDTOUS0003.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers(s.getUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(sellerDTOUS0003);
    }

    // Exercício US 0004
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserDTOUS0004> getUserById(@PathVariable Integer userId){
        User u = userService.getUserById(userId);
        UserDTOUS0004 userDTOUS0004 = UserDTOUS0004.builder()
                .userId(u.getId())
                .userName(u.getUserName())
                .followed(u.getSellers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(userDTOUS0004);
    }

    // Exercício US 0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToUnfollow
    ) {
        userService.unfollow(userId, userIdToUnfollow);
    }
}
