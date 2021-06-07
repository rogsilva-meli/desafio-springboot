package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
