package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    AppUserService appUserService;

    @PostMapping("/register")
    public String register(@ModelAttribute("SpringWeb") AppUser appUser) {

        if (appUser == null) {
            return "redirect:signup";
        }

        appUserService.register(appUser);

        return "redirect:home";
    }
}
