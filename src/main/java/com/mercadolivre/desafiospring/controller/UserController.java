package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.domain.entity.User;
import com.mercadolivre.desafiospring.service.SellerService;
import com.mercadolivre.desafiospring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value= "Retorna uma lista de usuários")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value= "Retorna uma lista de usuários")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        User u = userService.getUserById(userId);
        return ResponseEntity.ok(u);
    }


    @PostMapping("/users")
    @ApiOperation(value= "Cria um usuário")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTORegister user){
        User u = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    // Exercício US 0001
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ApiOperation(value= "Acionar usuário para seguir um vendedor")
    public ResponseEntity<Seller> addSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToFollow
    ) {
        Seller s = userService.followSeller(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    // Exercício US 0002
    @GetMapping("/users/{userId}/followers/count")
    @ApiOperation(value= "Retorna a quantidade de usuários que seguem um determinado vendedor ")
    public ResponseEntity<SellerDTO0002> getCountSellerByUser(@PathVariable Integer userId){
        SellerDTO0002 s = sellerService.countUsers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    // Exercício US 0003
    @GetMapping("/users/{userId}/followers/list")
    @ApiOperation(value= "Retorna uma lista de todos os usuários que seguem um determinado vendedor")
    public ResponseEntity<SellerDTO0003> getSellerById(@PathVariable Integer userId){
        Seller s = sellerService.getSellerById(userId);
        SellerDTO sDTO = SellerDTO.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers(s.getUsers())
                .build();

        SellerDTO0003 sellerDTOUS0003 = sellerService.convertSellerToSellerDTOUS0003(s);

        return ResponseEntity.status(HttpStatus.OK).body(sellerDTOUS0003);
    }

    // Exercício US 0004
    @GetMapping("/users/{userId}/followed/list")
    @ApiOperation(value= "Retorna um usuário pelo Id")
    public ResponseEntity<UserDTO0004> getUserById(@PathVariable Integer userId){
        User u = userService.getUserById(userId);
        UserDTO0004 userDTO0004 = userService.convertUserToUserDTOUS0004(u);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO0004);
    }

    // Exercício US 0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value= "Acionar usuário para deixar de seguir o vendedor")
    public void removeSellerToUser(
            @PathVariable Integer userId,
            @PathVariable Integer userIdToUnfollow
    ) {
        userService.unfollowSeller(userId, userIdToUnfollow);
    }

    // Exercício US 0008
    @GetMapping("/users/{userId}/followers")
    @ApiOperation(value= "Listar por ordem alfabética crescente ou decrescente os seguidores de um vendedor")
    public ResponseEntity<SellerDTO0003> getSellerById(@PathVariable Integer userId, @RequestParam String order){
        SellerDTO0003 s = sellerService.getSellersAsc(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    // Exercício US 0008
    @GetMapping("/users/{userId}/followed")
    @ApiOperation(value= "Listar por ordem alfabética crescente ou decrescente os seguidores de um usuário")
    public ResponseEntity<UserDTO0004> followedOrder(@PathVariable Integer userId, @RequestParam String order){
        UserDTO0004 u = userService.getUsersAsc(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
}
