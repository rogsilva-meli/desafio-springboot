package com.mercadolivre.desafiospring.domain.dto;

import com.mercadolivre.desafiospring.domain.entity.Seller;
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
public class UserDTO0003 {

    private int userId;
    private String userName;

}
