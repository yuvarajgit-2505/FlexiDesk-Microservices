package com.flexidesk.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flexidesk.auth.entity.User;
import com.flexidesk.auth.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder PasswordEncoder;
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.PasswordEncoder=passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User already exists with this email");
        }
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
    }
}
