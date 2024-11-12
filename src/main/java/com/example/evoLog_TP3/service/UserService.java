package com.example.evoLog_TP3.service;


import com.example.evoLog_TP3.controller.UserController;
import com.example.evoLog_TP3.model.User;
import com.example.evoLog_TP3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     *
     * @param user User object containing registration details.
     * @return The saved User object.
     */
    public User registerUser(User user) {
        // Check if user with email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists.");
        }
        return userRepository.save(user);
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param email    User's email.
     * @param password User's password.
     * @return The authenticated User object.
     */
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));
        logger.info("Searching user");
        // In a real application, passwords should be hashed and verified accordingly.
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password.");
        }
        return user;
    }

    // Other user-related methods if needed
}