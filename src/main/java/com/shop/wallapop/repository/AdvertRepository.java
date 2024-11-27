package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.usuario)"+
            "FROM Advertisement a ORDER BY a.createdAt DESC")
    List<Advertisement> obtainAllAdvertsDes();

    @Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.usuario)"+
            "FROM Advertisement a WHERE a.usuario.username=:adv_usr")
    List<AdvertDTO> obtainAllUserAds(@Param("adv_usr") String user);

    @Query("DELETE FROM Advertisement a WHERE a.id =:adv_id")
    void deleteAdvert(@Param("adv_id") Long id);

    @Query("SELECT new com.shop.wallapop.DTO.AdvertDTO(a.id,a.title,a.price,a.description,a.createdAt,a.usuario)"+
            "FROM Advertisement a WHERE a.id=:adv_id")
    List<AdvertDTO> obtainAdvert(@Param("adv_id") Long id);
    @Query("UPDATE Advertisement SET price=:priceCh, description=:desCh,title=:titCh "+
            "WHERE id=:idCh" )
    void updateAdvert(@Param("priceCh") double price,@Param("idCh") Long id, @Param("desCh") String desCh,@Param("titCh") String titCh);
}
