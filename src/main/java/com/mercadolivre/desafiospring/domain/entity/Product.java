package com.mercadolivre.desafiospring.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


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

    @NotBlank(message = "ProductName field can't be empty or null")
    private String productName;

    @NotBlank(message = "Type field can't be empty or null")
    private String type;

    @NotBlank(message = "Brand field can't be empty or null")
    private String brand;

    @NotBlank(message = "Color field can't be empty or null")
    private String color;

    @NotBlank(message = "Notes field can't be empty or null")
    private String notes;


}
