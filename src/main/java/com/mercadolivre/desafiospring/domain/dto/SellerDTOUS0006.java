package com.mercadolivre.desafiospring.domain.dto;

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
public class SellerDTOUS0006 {

    private int userId;
    private List<PostDTOUS0006> posts = new ArrayList<>();
}
