package com.mercadolivre.desafiospring.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class User extends BaseEntity{

    private String userName;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<Seller> sellers = new ArrayList<>();

}
