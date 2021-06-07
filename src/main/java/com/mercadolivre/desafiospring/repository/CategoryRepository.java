package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
