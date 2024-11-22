package com.shop.wallapop.repository;

import com.shop.wallapop.DTO.PictureDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository {
    //todo Comprobar que funciona a la hora de cargar las imágenes
    @Query("SELECT new com.shop.wallapop.DTO.PictureDTO(p.id,p.name,p.advertisement)"+
            "FROM Picture p")
    List<PictureDTO> obtainAllPictures();
    @Query("SELECT new com.shop.wallapop.DTO.PictureDTO(p.id,p.name,p.advertisement)"+
            //todo Preguntar a Samuel sobre cómo funciona bien esto
            "FROM Picture p WHERE p.id= :advertId")
    List<PictureDTO> obtainAllPicturesById(@Param("advertId") Long advertId);
}
