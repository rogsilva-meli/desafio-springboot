package com.mercadolivre.desafiospring.repository;

import com.mercadolivre.desafiospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
