package com.mercadolivre.desafiospring.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Category extends BaseEntity{

    @NotBlank(message = "Name field can't be empty or null")
    private String name;
}
