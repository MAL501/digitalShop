package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {

    /*@Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.user)"+
            "FROM Advertisement a ORDER BY a.createdAt DESC")
    List<Advertisement> obtainAllAdvertsDes();*/

    @Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.user)"+
            "FROM Advertisement a WHERE a.user.username=:adv_usr")
    List<Advertisement> obtainAllUserAds(@Param("adv_usr") String user);

    @Query("DELETE FROM Advertisement a WHERE a.id =:adv_id")
    void deleteAdvert(@Param("adv_id") Long id);

}
