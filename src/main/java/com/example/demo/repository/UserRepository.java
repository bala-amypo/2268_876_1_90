package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User user);
    List<User> findAll();
}
