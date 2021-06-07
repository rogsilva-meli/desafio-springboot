package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
