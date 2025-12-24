package com.example.demo.service;

import com.example.demo.model.User;
import java.util.*;

public interface UserService {
    User registerUser(User u);
    User getUser(Long id);
    List<User> getAllUsers();
}
