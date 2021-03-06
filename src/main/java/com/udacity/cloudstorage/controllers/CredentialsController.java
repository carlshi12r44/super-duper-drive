package com.udacity.cloudstorage.controllers;

import com.udacity.cloudstorage.models.AppUser;
import com.udacity.cloudstorage.models.Credentials;
import com.udacity.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsController {

  @Autowired
  private CredentialsService credentialsService;

  @PostMapping("/credentials")
  public String saveOrUpdateCredentials(Authentication authentication, Credentials credential) {
    AppUser appUser = (AppUser) authentication.getPrincipal();
    if (credential.getCredentialid() > 0) {
      credentialsService.updateCredential(credential);
    } else {
      credentialsService.addCredential(credential, appUser.getUserid());
    }
    return "redirect:/result?success";
  }

  @GetMapping("/credentials/delete")
  public String deleteNote(@RequestParam("id") int credentialid) {
    if (credentialid > 0) {
      credentialsService.deleteCredential(credentialid);
      return "redirect:/result?success";
    }
    return "redirect:/result?error";
  }

}
