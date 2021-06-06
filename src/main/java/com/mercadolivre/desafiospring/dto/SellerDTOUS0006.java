package com.mercadolivre.desafiospring.dto;

import com.mercadolivre.desafiospring.entity.Post;
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
public class SellerDTOUS0006 {

    private int userId;
    private Set<PostDTOUS0006> posts = new HashSet<>();
}
