package com.mercadolivre.desafiospring.domain.dto;

import com.mercadolivre.desafiospring.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDTOUS0003 {

    private int userId;
    private String userName;
    private List<User> followers = new ArrayList<>();
}
