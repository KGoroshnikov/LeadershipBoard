package com.example;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner addUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(new User("admin", passwordEncoder.encode("admin123"), User.Role.ADMIN, ""));
            }
            if (userRepository.findByUsername("user") == null) {
                userRepository.save(new User("user", passwordEncoder.encode("user123"), User.Role.USER, ""));
            }
        };
    }

}