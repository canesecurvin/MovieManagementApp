package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByDisplayName(String username);
    Optional<User> findByEmail(String email);
    boolean existsByDisplayName(String username);
    boolean existsByEmail(String email);
    Optional<User> findByDisplayNameOrEmail(String displayName, String email);

}
