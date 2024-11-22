package com.shop.wallapop.service;

import com.shop.wallapop.entity.User;
import com.shop.wallapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    //todo Investigar cómo arreglar esto
    @Override
    public UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.getUserByUsername(username)
                .orElseThrow(()->UsernameNotFoundException("Usuairo no encontrado"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
