package com.example.demo.repository;

import com.example.demo.repository.model.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByEmail(String email);
}
