package com.example.demo.repository;

import com.example.demo.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntityUserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByEmail(String email);
}
