package com.shop.wallapop.service;

import com.shop.wallapop.entity.Usuario;
import com.shop.wallapop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Usuario> obtainUser(String email) {
        Optional<Usuario> usuario = userRepository.findByEmail(email);
        return usuario;
    }
    public void save(Usuario usuario) {
        userRepository.save(usuario);
    }
}
