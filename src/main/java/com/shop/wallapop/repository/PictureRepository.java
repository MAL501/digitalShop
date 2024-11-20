package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.PictureDTO;
import com.shop.wallapop.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("SELECT new com.shop.wallapop.DTO.PictureDTO(p.id,p.name,p.advertisement)"+
            "FROM Picture p WHERE p.id= :pictureId")
    List<PictureDTO> findAllById(@Param("pictureId") Long id);
    @Query("SELECT new com.shop.wallapop.DTO.PictureDTO(p.id,p.name,p.advertisement)"+
            "FROM Picture p")
    List<PictureDTO> findAllPictures();
    /*
    * SELECT a.id AS advert_id, a.title AS advert_title, p.id AS picture_id, p.name AS picture_name
    * FROM adverts a
    * JOIN (SELECT advertisement_id, MIN(id) AS first_picture_id
    *       FROM pictures GROUP BY advertisement_id)
    * AS first_pictures ON a.id = first_pictures.advertisement_id JOIN pictures p ON first_pictures.first_picture_id = p.id;
     * */


}
