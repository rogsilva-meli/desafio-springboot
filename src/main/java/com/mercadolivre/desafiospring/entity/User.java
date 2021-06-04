package com.mercadolivre.desafiospring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<Seller> sellers = new HashSet<>();

}
