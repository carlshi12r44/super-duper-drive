package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.CredentialsMapper;
import com.udacity.cloudstorage.models.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsMapper credentialsMapper;

    public List<Credentials> getAllCredentials(int userid) throws Exception {
        List<Credentials> credentials = credentialsMapper.findByUserId(userid);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials;
    }

    public void addCredential(Credentials credential, int userid) {
        credentialsMapper.insertCredentials(credential, userid);
    }

    public void updateCredential(Credentials credential) {
        credentialsMapper.updateCredentials(credential);
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredentials(credentialid);
    }

}
