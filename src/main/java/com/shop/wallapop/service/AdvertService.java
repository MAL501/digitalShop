package com.shop.wallapop.service;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    public List<AdvertDTO> obtainAdverts() {
        return advertRepository.obtainAllAdvertsDes();
    }
    public List<AdvertDTO> obtainAllUserAds(String user) {
        List<AdvertDTO> ret= advertRepository.obtainAllUserAds(user);
        return ret;
    }
    public void deleteAdvertById(Long id) {
        advertRepository.deleteById(id);
    }
    public AdvertDTO findAdvertById(Long id) {
        AdvertDTO advert= advertRepository.obtainAdvert(id);
        return advert;
    }
}
