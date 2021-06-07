package com.mercadolivre.desafiospring.domain.dto;

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
public class SellerDTO00012 {

    private int userId;
    private List<PostDTOPromo00012> posts = new ArrayList<>();
}
