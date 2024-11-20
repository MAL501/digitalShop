package com.shop.wallapop.service;

import com.shop.wallapop.DTO.PictureDTO;
import com.shop.wallapop.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    public List<PictureDTO> getPictures(){
        return pictureRepository.findAllPictures();
    }
}
