package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.UserDTO;
import com.shop.wallapop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT new com.shop.wallapop.DTO.UserDTO(u.id,u.username,u.email,u.password,u.phone,u.poblation)"+
            "FROM User u")
    List<UserDTO> findAllUsers();
    @Query("SELECT new com.shop.wallapop.DTO.UserDTO(u.id,u.username,u.email,u.password,u.phone,u.poblation)"+
            "FROM User u WHERE u.id=:usr_id")
    UserDTO findUserById(@Param("usr_id") Long id);

}
