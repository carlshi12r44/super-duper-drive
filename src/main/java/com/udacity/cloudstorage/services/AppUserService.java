package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.AppUserMapper;
import com.udacity.cloudstorage.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private PasswordEncoder encoder;

    public AppUser register(AppUser appUser) {
        String encodedPW = encoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPW);
        appUser.setEnabled(true);
        appUser.setRole("USER");
        appUserMapper.insertUser(appUser);
        return appUser;
    }

}
