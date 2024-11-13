package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {
    @Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.user)"+
            "FROM Advertisement a ORDER BY a.createdAt DESC")
    List<AdvertDTO> obtainAllAdvertsDes();
}
