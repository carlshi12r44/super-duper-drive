package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.models.Files;
import com.udacity.cloudstorage.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilesController {

    @Autowired
    private FilesService filesService;

    @PostMapping("/files")
    public String saveFile(Authentication authentication, Files file) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        filesService.addFile(file, appUser.getUserid());
        return "redirect:home";
    }

}
