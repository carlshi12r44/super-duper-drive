package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.models.Credentials;
import com.udacity.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/credentials")
    public String saveCredentials(Authentication authentication, Credentials credential) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        credentialsService.addCredential(credential, appUser.getUserid());
        return "redirect:home";
    }

}
