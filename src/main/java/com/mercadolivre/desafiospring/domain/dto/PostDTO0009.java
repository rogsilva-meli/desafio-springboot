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
public class PostDTO0009 {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Product product;
}
