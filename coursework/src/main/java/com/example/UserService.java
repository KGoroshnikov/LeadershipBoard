package com.example;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found " + username));
    }

    public void updateUser(String name, String email, String password) {
        User user = getCurrentUser();
        user.setEmail(email);
        user.setUsername(name);
        if (password != null) {
            user.setPassword(password);
        }
        userRepository.save(user);
    }
}
