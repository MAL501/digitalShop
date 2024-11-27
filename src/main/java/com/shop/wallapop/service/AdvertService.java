package com.shop.wallapop.service;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    public List<Advertisement> obtainAdverts() {
        return advertRepository.findAll();
        //return advertRepository.obtainAllAdvertsDes();
    }
    public List<Advertisement> obtainAllUserAds(String user) {
        List<Advertisement> ret= advertRepository.obtainAllUserAds(user);
        return ret;
    }
    public void deleteAdvertById(Long id) {
        advertRepository.deleteById(id);
    }
    public Optional<Advertisement> findAdvertById(Long id) {
        Optional<Advertisement> advert= advertRepository.findById(id);
        return advert;
    }
}
