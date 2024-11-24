package com.shop.wallapop.service;

import com.shop.wallapop.entity.Usuario;
import com.shop.wallapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<Usuario> obtainUser(String email) {
        Optional<Usuario> usuario = userRepository.findByEmail(email);
        return usuario;
    }
}
