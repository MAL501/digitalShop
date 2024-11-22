package com.shop.wallapop.service;


import com.shop.wallapop.DTO.PictureDTO;
import com.shop.wallapop.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public List<PictureDTO>obtainPictures() {return pictureRepository.obtainAllPictures();}
    public List<PictureDTO>obtainPicturesById(Long id) {return pictureRepository.obtainAllPicturesById(id);}
}
