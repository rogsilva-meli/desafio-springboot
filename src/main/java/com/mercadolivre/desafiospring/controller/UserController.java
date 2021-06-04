package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.controller.dto.SellerDTO;
import com.mercadolivre.desafiospring.controller.dto.SellerDTOUS003;
import com.mercadolivre.desafiospring.controller.dto.UserDTO;
import com.mercadolivre.desafiospring.controller.dto.UserDTOUS004;
import com.mercadolivre.desafiospring.entity.Seller;
import com.mercadolivre.desafiospring.entity.User;
import com.mercadolivre.desafiospring.service.SellerService;
import com.mercadolivre.desafiospring.service.UserService;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.util.xml.TransformerUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Seller> addSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToFollow
    ) {
        Seller s = userService.addSellerToUser(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

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

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<SellerDTOUS003> getSellerById(@PathVariable Integer userId){
        Seller s = sellerService.getSellerById(userId);
        SellerDTOUS003 sellerDTOUS003 = SellerDTOUS003.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers(s.getUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(sellerDTOUS003);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserDTOUS004> getUserById(@PathVariable Integer userId){
        User u = userService.getUserById(userId);
        UserDTOUS004 userDTOUS004 = UserDTOUS004.builder()
                .userId(u.getId())
                .userName(u.getUserName())
                .followed(u.getSellers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(userDTOUS004);
    }





}
