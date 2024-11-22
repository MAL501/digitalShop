package com.shop.wallapop.service;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final UserService userService;
    @Autowired
    public AdvertService(AdvertRepository advertRepository, UserService userService) {
        this.advertRepository = advertRepository;
        this.userService =userService;
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
    public List<AdvertDTO> findAdvertById(Long id) {
        List<AdvertDTO> advert= advertRepository.obtainAdvert(id);
        return advert;
    }
    public String saveAdvert(AdvertDTO advert) {
        Long idAdv= Long.getLong("1");
        //advert.setUsuario(userService.obtainUser(idAdv));
        advert.setCreatedAt(LocalDateTime.now());
        //todo Convertir el AdvertDTO en un Advert
        //advertRepository.save(advert);
        return "redirect://anuncios";
    }
    //todo convertir el usuario del dto en un Usuario
    public Advertisement dtoToAdvertisement(AdvertDTO advert) {
        Advertisement advertisement= new Advertisement();
        advertisement.setTitle(advert.getTitle());
        advertisement.setDescription(advert.getDescription());
        advertisement.setPrice(advert.getPrice());
        return advertisement;
    }
}
