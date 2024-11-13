package com.shop.wallapop.controller;

import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertController {
    @Autowired
    private AdvertService advertService;
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/advert";
    }
    @GetMapping("/advert")
    public String advert(Model model) {
        //List<Advertisement> adverts=advertService.getAdverts();
        //model.addAttribute("adverts",adverts);
        return "advert-list";
    }

}
