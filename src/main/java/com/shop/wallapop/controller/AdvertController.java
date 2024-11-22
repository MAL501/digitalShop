package com.shop.wallapop.controller;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.service.AdvertService;
import com.shop.wallapop.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final PictureService pictureService;
    @Autowired
    public AdvertController(AdvertService advertService,PictureService pictureService) {
        this.advertService = advertService;
        this.pictureService = pictureService;
    }
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/anuncios";
    }
    @GetMapping("/anuncios")
    public String advert(Model model) {
        Integer count=0;
        List<AdvertDTO> adverts=advertService.obtainAdverts();
        count=adverts.size();
        model.addAttribute("count",count);
        model.addAttribute("adverts",adverts);
        return "advert-list";
    }
    @GetMapping("/anuncios/{user}")
    public String advert(@PathVariable String user, Model model) {
        Integer count=0;
        List<AdvertDTO> adverts=advertService.obtainAllUserAds(user);
        count=adverts.size();
        model.addAttribute("count",count);
        model.addAttribute("adverts",adverts);
        return "advert-list";
    }
    @GetMapping("/anuncios/{user}/{id}/del")
    public String advert(@PathVariable String user, @PathVariable Long id, Model model) {
        Integer count=0;
        advertService.deleteAdvertById(id);
        List<AdvertDTO> adverts=advertService.obtainAllUserAds(user);
        count=adverts.size();
        model.addAttribute("adverts",adverts);
        model.addAttribute("count",count);
        return "advert-list";
    }
    @GetMapping("/anuncios/ver/{id}")
    public String advert(@PathVariable Long id, Model model) {
        Integer count=1;
        List<AdvertDTO> advert= advertService.findAdvertById(id);
        model.addAttribute("adverts",advert);
        model.addAttribute("count",count);
        return "advert-list";
    }
    @GetMapping("/anuncios/new")
    public String newAdvert(Model model) {
        AdvertDTO advertisement=new AdvertDTO();
        model.addAttribute("advert",advertisement);
        return "advert-create";
    }
    @PostMapping("/anuncios/new")
    public String newAdvert(AdvertDTO advertisement) {
        advertisement.setCreatedAt(LocalDateTime.now());
        //advertisement.setUsuario();
        advertService.saveAdvert(advertisement);
        return "redirect:/anuncios";
    }


}
