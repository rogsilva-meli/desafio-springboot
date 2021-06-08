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

    @NotBlank(message = "{name.not.blank}")
    private String name;
}
