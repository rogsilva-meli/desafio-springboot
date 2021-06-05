package com.mercadolivre.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDTO {

    private int userId;
    private String userName;
    private int followers_count;
}
