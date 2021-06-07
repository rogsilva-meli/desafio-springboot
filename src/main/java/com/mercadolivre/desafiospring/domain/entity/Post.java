package com.mercadolivre.desafiospring.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Post extends BaseEntity{

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

    private boolean hasPromo;

    private double discount;

}
