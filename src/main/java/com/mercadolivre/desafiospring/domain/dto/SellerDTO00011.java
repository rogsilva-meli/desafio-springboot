package com.mercadolivre.desafiospring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDTO00011 {

    private int userId;
    private String userName;
    private int promoproducts_count;

}
