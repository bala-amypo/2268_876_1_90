package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.*;

public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
