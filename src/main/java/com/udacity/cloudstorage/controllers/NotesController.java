package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.models.Notes;
import com.udacity.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/notes")
    @ResponseBody
    public String createNote(Authentication authentication, Notes note) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        notesService.addNote(note, appUser.getUserid());
        return "redirect:home";
    }

}
