package com.shop.wallapop.service;

import com.shop.wallapop.DTO.UserDTO;
import com.shop.wallapop.entity.User;
import com.shop.wallapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDTO obtainUser(Long id) {
        UserDTO userDTO = userRepository.findUserById(id);
        return userDTO;
    }
}
