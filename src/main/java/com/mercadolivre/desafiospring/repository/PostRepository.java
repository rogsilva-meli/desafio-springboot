package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
