package com.mercadolivre.desafiospring.dto;

import com.mercadolivre.desafiospring.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDTOUS0003 {

    private int userId;
    private String userName;
    private Set<User> followers = new HashSet<>();
}
