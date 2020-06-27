package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.services.CredentialsService;
import com.udacity.cloudstorage.services.FilesService;
import com.udacity.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @Autowired
  private NotesService notesService;

  @Autowired
  private CredentialsService credentialsService;

  @Autowired
  private FilesService filesService;

  @GetMapping(value = {"/", "/home"})
  public ModelAndView getHomePage(Authentication authentication) throws Exception {
    AppUser appUser = (AppUser) authentication.getPrincipal();
    ModelAndView modelAndView = new ModelAndView("home");
    modelAndView.addObject("notes", notesService.getAllNotes(appUser.getUserid()));
    modelAndView.addObject("credentials",
                           credentialsService.getAllCredentials(appUser.getUserid()));
    modelAndView.addObject("files", filesService.getAllFiles(appUser.getUserid()));
    return modelAndView;
  }

}
