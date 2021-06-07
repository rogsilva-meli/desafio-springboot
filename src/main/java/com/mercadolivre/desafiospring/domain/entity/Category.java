package com.mercadolivre.desafiospring.domain.entity;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Category extends BaseEntity{
    private String name;
}
