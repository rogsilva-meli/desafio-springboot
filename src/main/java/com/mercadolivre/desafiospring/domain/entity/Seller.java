package com.mercadolivre.desafiospring.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Seller extends BaseEntity{

    private String sellerName;

    @ManyToMany
    @JoinTable(
            name = "following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id")
    )
    public List<User> users = new ArrayList<>();

    //private int followers_count;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Post> posts = new ArrayList<>();

}
