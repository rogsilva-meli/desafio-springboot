package com.mercadolivre.desafiospring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    public Set<User> users = new HashSet<>();

    private int followers_count;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private Set<Post> posts = new HashSet<>();
}
