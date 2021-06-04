package com.mercadolivre.desafiospring.controller.dto;

import com.mercadolivre.desafiospring.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOUS004 {

    private int userId;
    private String userName;
    private Set<Seller> followed = new HashSet<>();


}
