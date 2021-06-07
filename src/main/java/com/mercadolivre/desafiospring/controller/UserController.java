package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.SellerDTO;
import com.mercadolivre.desafiospring.domain.dto.SellerDTOUS0003;
import com.mercadolivre.desafiospring.domain.dto.UserDTO;
import com.mercadolivre.desafiospring.domain.dto.UserDTOUS0004;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.domain.entity.User;
import com.mercadolivre.desafiospring.service.SellerService;
import com.mercadolivre.desafiospring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        Seller s = userService.followSeller(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    // Exercício US 0002
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<SellerDTO> getCountSellerByUser(@PathVariable Integer userId){
        SellerDTO s = sellerService.countUsers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    // Exercício US 0003
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<SellerDTOUS0003> getSellerById(@PathVariable Integer userId){
        Seller s = sellerService.getSellerById(userId);
        SellerDTOUS0003 sellerDTOUS0003 = sellerService.convertSellerToSellerDTOUS0003(s);
        return ResponseEntity.status(HttpStatus.OK).body(sellerDTOUS0003);
    }

    // Exercício US 0004
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserDTOUS0004> getUserById(@PathVariable Integer userId){
        User u = userService.getUserById(userId);
        UserDTOUS0004 userDTOUS0004 = userService.convertUserToUserDTOUS0004(u);
        return ResponseEntity.status(HttpStatus.OK).body(userDTOUS0004);
    }

    // Exercício US 0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToUnfollow
    ) {
        userService.unfollowSeller(userId, userIdToUnfollow);
    }

    // Exercício US 0008 - FALTA ARRUMAR AS ORDENAGENS
    @GetMapping("/users/{userId}/followers")
    public ResponseEntity<UserDTO> lista(@PathVariable Integer userId, @RequestParam String order){
        User u = userService.getUsersAsc(userId, order);
        UserDTO userDTO = userService.convertUserToUserDTO(u);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
}
