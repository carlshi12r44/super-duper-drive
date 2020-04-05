package com.udacity.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppUser {
    private int userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;
    private Boolean enabled;
    private String role;
}
