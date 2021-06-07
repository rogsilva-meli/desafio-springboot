package com.mercadolivre.desafiospring.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolivre.desafiospring.domain.entity.Product;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO{

    private Integer userId;

    private Integer id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Product detail;

    private Integer category;

    private Double price;


}
