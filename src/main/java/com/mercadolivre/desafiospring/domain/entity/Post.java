package com.mercadolivre.desafiospring.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Post extends BaseEntity{

    @NotNull(message = "Date field can't be empty or null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Category field can't be empty or null")
    @OneToOne(cascade=CascadeType.ALL)
    public Category category;

    @NotNull(message = "Price field can't be empty or null")
    @Digits(integer = 18, fraction = 2)
    @Positive(message = "Price field can't be negative")
    private Double price;

    @NotNull(message = "Product field can't be empty or null")
    @OneToOne(cascade=CascadeType.ALL)
    public Product product;

    @NotNull(message = "Seller field can't be empty or null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    private boolean hasPromo;

    private double discount;

}
