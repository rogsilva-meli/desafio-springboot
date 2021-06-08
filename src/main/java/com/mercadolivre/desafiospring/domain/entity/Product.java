package com.mercadolivre.desafiospring.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @NotNull
    private String productName;

    @NotNull
    private String type;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
    private String notes;


}
