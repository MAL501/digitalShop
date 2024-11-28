package com.shop.wallapop.controller;

import com.shop.wallapop.DTO.AdvertDTO;
import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.Picture;
import com.shop.wallapop.entity.User;
import com.shop.wallapop.repository.UserRepository;
import com.shop.wallapop.service.AdvertService;
import com.shop.wallapop.service.PictureService;
import com.shop.wallapop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final PictureService pictureService;
    private final UserRepository userRepository;
    private final UserService userService;
    private User user;
    @Autowired
    public AdvertController(AdvertService advertService, PictureService pictureService, UserRepository userRepository, UserService userService) {
        this.advertService = advertService;
        this.pictureService = pictureService;
        this.user = new User();
        this.user.setPhone("66666");
        this.user.setPoblation("Cuenca");
        this.userRepository = userRepository;
        this.userService = userService;
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
    public String loginPost(@RequestParam(value = "userName") String name, @RequestParam(value = "password") String password) {
        Optional<User> optional=userService.obtainUser(name);
        User usuario=optional.get();
        if(password.equals(usuario.getPassword())&&name.equals(usuario.getUsername())) {
            this.user=usuario;
            return "redirect:/anuncios";
        }else{
            return "redirect:/login";
        }
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", this.user);
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid User user){
        this.user = user;
        userRepository.save(this.user);
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
    @GetMapping("/misAnuncios")
    public String myAdvert(Model model) {
        Integer count=0;
        List<Advertisement> adverts=advertService.obtainAllUserAds(this.user.getUsername());
        count=adverts.size();
        model.addAttribute("count",count);
        model.addAttribute("adverts",adverts);
        return "advert-list";
    }
    @GetMapping("/anuncios/{id}/del")
    public String advert( Model model,@PathVariable Long id) {
        advertService.deleteAdvertById(id,this.user);
        return "redirect:/anuncios";
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

        model.addAttribute("advert",new Advertisement());
        return "advert-new";
    }
    @PostMapping("/anuncios/new")
    public String newAdvert(@Valid Advertisement advert,
                            @RequestParam(value = "picturesFiles") List<MultipartFile> picturesFiles,
                            Model model) {
        try{
            pictureService.guardarFotos(picturesFiles,advert);
        }catch(IllegalArgumentException ex){
            model.addAttribute("mensaje",ex.getMessage());
            model.addAttribute("advert",new Advertisement());
        }
        advert.setUser(user);
        advert.setCreatedAt(LocalDateTime.now());
        advertService.saveAdvert(advert);
        return "redirect:/anuncios/ver/"+advert.getId();
    }
    @GetMapping("/anuncios/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        try{
            Optional<Advertisement> advert= advertService.findAdvertById(id);
            model.addAttribute("advert",advert.get());
            return "advert-edit";
        }catch(IllegalArgumentException ex){
            return "redirect:/anuncios";
        }
    }
    @PostMapping("/anuncios/{id}/edit")
    public String editAdvert(@PathVariable("id")Long id, Advertisement advert){
        advert.setId(id);
        advertService.saveAdvert(advert);
        return "redirect:/anuncios/ver/"+advert.getId();
    }
    @GetMapping("/anuncios/{id1}/pictures/{id2}/del")
    public String delete(@PathVariable Long idAdvert, @PathVariable Long idPicture, Model model) {
        pictureService.deletePicture(idPicture);
        return "redirect:/anuncios/"+idAdvert+"/edit";
    }
    @PostMapping("/anuncios/{id}/edit/addPictures")
    public String addPictures(@PathVariable Long idAdvert,
                              @RequestParam(value = "picturesFiles") MultipartFile archivoPicture){
        Optional<Advertisement> advert= advertService.findAdvertById(idAdvert);
        if(advert.isPresent()){
            pictureService.addFoto(archivoPicture, advert.get());
        }
        return "redirect:/anuncios/"+idAdvert+"/edit";
    }
    @GetMapping("/logout")
    public String logout(){
        this.user = new User();
        return "redirect:/login";
    }
}
