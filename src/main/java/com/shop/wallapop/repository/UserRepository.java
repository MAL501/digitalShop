package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository{
    @Query("SELECT new com.shop.wallapop.DTO.UserDTO(u.id,u.username,u.password,u.email,u.phone,u.poblation)"+
            "FROM User u")
    List<UserDTO> getUsers();
    @Query("SELECT new com.shop.wallapop.DTO.UserDTO(u.id,u.username,u.password,u.email,u.phone,u.poblation)"+
            "FROM User u WHERE u.username=:n_username")
    UserDTO getUserByUsername(@Param("n_username") String username);
}
