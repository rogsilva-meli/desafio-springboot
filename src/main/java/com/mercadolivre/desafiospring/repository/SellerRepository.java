package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
