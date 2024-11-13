package com.shop.wallapop.service;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    @Autowired
    private AdvertRepository advertRepository;

    public List<AdvertDTO> obtainAdverts() {
        return advertRepository.obtainAllAdvertsDes();
    }
}
