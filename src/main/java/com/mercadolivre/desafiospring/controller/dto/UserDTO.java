package com.mercadolivre.desafiospring.controller.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.mercadolivre.desafiospring.entity.Seller;
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
public class UserDTO {

    private int userId;
    private String userName;
    private Set<Seller> followers = new HashSet<>();


}
