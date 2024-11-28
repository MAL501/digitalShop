package com.shop.wallapop.service;

import com.shop.wallapop.entity.User;
import com.shop.wallapop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> obtainUser(String username) {
        Optional<User> usuario = userRepository.findByUsername(username);
        return usuario;
    }
    public void save(User usuario) {
        userRepository.save(usuario);
    }
}
