package com.mercadolivre.desafiospring.domain.dto;

import com.mercadolivre.desafiospring.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDTO0009 {

    private int userId;
    private String userName;
    private List<PostDTO0009> products = new ArrayList<>();
}
