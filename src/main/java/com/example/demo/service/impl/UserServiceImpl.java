package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User u) {
        if (u == null) throw new IllegalArgumentException("user is null");
        if (u.getEmail() == null || u.getEmail().isBlank()) throw new IllegalArgumentException("email required");
        if (userRepository.existsByEmail(u.getEmail())) {
            throw new IllegalArgumentException("email already exists");
        }
        return userRepository.save(u);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
