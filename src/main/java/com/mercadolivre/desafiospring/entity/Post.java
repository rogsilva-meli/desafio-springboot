package com.mercadolivre.desafiospring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Post implements Comparable<Post>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @OneToOne(cascade=CascadeType.ALL)
    public Category category;

    private Double price;

    @OneToOne(cascade=CascadeType.ALL)
    public Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;


    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.date);
    }
}
