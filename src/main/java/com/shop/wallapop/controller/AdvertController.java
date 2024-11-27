package com.shop.wallapop.controller;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.User;
import com.shop.wallapop.service.AdvertService;
import com.shop.wallapop.service.PictureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final PictureService pictureService;
    private User user;
    @Autowired
    public AdvertController(AdvertService advertService,PictureService pictureService) {
        this.advertService = advertService;
        this.pictureService = pictureService;
        this.user = new User();
        this.user.setPhone("66666");
        this.user.setPoblation("Cuenca");
    }
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", this.user);
        return "login";
    }
    @PostMapping("/login")
    public String loginPost(Model model) {
        this.user.setEmail(this.user.getUsername()+"@gmail.com");
        return "redirect:/anuncios";
    }
    @GetMapping("/anuncios")
    public String advert(Model model) {
        Integer count=0;
        List<Advertisement> adverts=advertService.obtainAdverts();
        count=adverts.size();
        model.addAttribute("count",count);
        model.addAttribute("adverts",adverts);
        return "advert-list";
    }
    @GetMapping("/anuncios/{user}")
    public String advert(@PathVariable String user, Model model) {
        Integer count=0;
        List<Advertisement> adverts=advertService.obtainAllUserAds(user);
        count=adverts.size();
        model.addAttribute("count",count);
        model.addAttribute("adverts",adverts);
        return "advert-list";
    }
    @GetMapping("/anuncios/{user}/{id}/del")
    public String advert(@PathVariable String user, @PathVariable Long id, Model model) {
        Integer count=0;
        advertService.deleteAdvertById(id);
        List<Advertisement> adverts=advertService.obtainAllUserAds(user);
        count=adverts.size();
        model.addAttribute("adverts",adverts);
        model.addAttribute("count",count);
        return "advert-list";
    }
    @GetMapping("/anuncios/ver/{id}")
    public String advert(@PathVariable Long id, Model model) {
        Optional<Advertisement> advert= advertService.findAdvertById(id);
        Advertisement advertisement=advert.get();
        model.addAttribute("advert",advertisement);
        return "advert-view";
    }
    @GetMapping("/anuncios/new")
    public String newAdvert(Model model) {
        Advertisement advertisement=new Advertisement();
        advertisement.setUser(user);
        advertisement.setCreatedAt(LocalDateTime.now());
        model.addAttribute("advert",new Advertisement());
        return "advert-new";
    }
    @PostMapping("/anuncios/new")
    public String newAdvert(@Valid Advertisement advert){

        return "redirect:/anuncios/ver/"+advert.getId();
    }

}
