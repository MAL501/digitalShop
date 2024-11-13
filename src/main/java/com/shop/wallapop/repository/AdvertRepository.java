package com.shop.wallapop.repository;

import com.shop.wallapop.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {
    //@Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.user)"+
    //        "FROM Advert a ")
}
