package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.AppUserMapper;
import com.udacity.cloudstorage.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) {
        return appUserMapper.findByUsername(username);
    }

    public AppUser register(AppUser appUser) {
        String encodedPW = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPW);
        appUser.setEnabled(true);
        appUser.setRole("USER");
        appUserMapper.insertUser(appUser);
        return appUser;
    }

}
