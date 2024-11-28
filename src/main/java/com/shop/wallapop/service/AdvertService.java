package com.shop.wallapop.service;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.User;
import com.shop.wallapop.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    @Autowired
    AdvertRepository advertRepository;
    @Autowired
    PictureService pictureService;
    public List<Advertisement> obtainAdverts() {
        return advertRepository.findAll();
        //return advertRepository.obtainAllAdvertsDes();
    }
    public List<Advertisement> obtainAllUserAds(String user) {
        List<Advertisement> ret= advertRepository.obtainAllUserAds(user);
        return ret;
    }
    public void deleteAdvertById(Long id, User user) {
        Advertisement advert = advertRepository.findById(id).orElse(null);
        if (advert != null) {
            if (advert.getUser()==user){
                advertRepository.delete(advert);
            }
        }
    }
    public Optional<Advertisement> findAdvertById(Long id) {
        Optional<Advertisement> advert= advertRepository.findById(id);
        return advert;
    }
    public void saveAdvert(Advertisement advert) {
        advertRepository.save(advert);
    }
}
