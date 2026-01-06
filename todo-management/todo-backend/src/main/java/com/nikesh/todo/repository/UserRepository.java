package com.nikesh.todo.repository;

import com.nikesh.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String userName, String email);

    Boolean existsByUsername(String username);
}
