package com.shop.wallapop.controller;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.Picture;
import com.shop.wallapop.entity.Usuario;
import com.shop.wallapop.service.AdvertService;
import com.shop.wallapop.service.PictureService;
import com.shop.wallapop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final PictureService pictureService;
    //todo Cuando todo funcione implementar
    /*private final PasswordEncoder passwordEncoder;*/
    private final UserService userService;

    @Autowired
    public AdvertController(AdvertService advertService, PictureService pictureService, /*PasswordEncoder passwordEncoder,*/ UserService userService) {
        this.advertService = advertService;
        this.pictureService = pictureService;
        /*this.passwordEncoder = passwordEncoder;*/
        this.userService = userService;
    }
    @GetMapping("/")
    public String index(Model model) {
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
        Advertisement advertisement=new Advertisement();
        model.addAttribute("advert",advertisement);
        return "advert-create";
    }
    @PostMapping("/anuncios/new")
    public String newAdvert(Advertisement advertisement, Usuario usuairo) {
        advertisement.setCreatedAt(LocalDateTime.now());
        advertisement.setUsuario(usuairo);
        advertService.saveAdvert(advertisement);
        return "redirect:/anuncios";
    }
    @GetMapping("/register")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/register")
    public String alta(Model model, @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registro";
        } else {
            /*String encryptedPassword = passwordEncoder.encode(usuario.getPassword());*/
            usuario.setRol("ADMIN");
            /*usuario.setPassword(encryptedPassword);*/
            userService.save(usuario);
            return "redirect:/login";
        }
    }
    @GetMapping("/anuncios/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("advert",advertService.findAdvertById(id));
        return "advert-edit";
    }
    @PostMapping("/anuncios/{id}/edit")
    public String editar(Model model, @Valid Advertisement advertisement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "advert-edit";
        }else{
            advertService.actualizarAdvert(advertisement);
        }
        return "redirect:/anuncios/{id}";
    }

    @GetMapping("/misAnuncios")
    public String misAnuncios(Model model, Usuario usuairo) {
        model.addAttribute("adverts",advertService.obtainAllUserAds(usuairo.getEmail()));
        return "advert-list";
    }
    @GetMapping("/anuncios/{user}/{id}/edit")
    public String edit(@PathVariable Long user, @PathVariable Long id, Model model, Usuario usuario) {
        if(!user.equals(usuario.getUsername())){
            model.addAttribute("adverts",advertService.obtainAdverts());
            return "redirect:/anuncios";
        }else{
            model.addAttribute("advert",advertService.findAdvertById(id));
            return "advert-edit";
        }
    }
}

