package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.CredentialsMapper;
import com.udacity.cloudstorage.models.Credentials;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

  @Autowired
  private CredentialsMapper credentialsMapper;

  @Autowired
  private EncryptionService encryptionService;

  public List<Credentials> getAllCredentials(int userid) throws Exception {
    List<Credentials> credentials = credentialsMapper.findByUserId(userid);
    if (credentials == null) {
      throw new Exception();
    }
    return credentials.stream().map(this::decryptPassword).collect(Collectors.toList());
  }

  private Credentials decryptPassword(Credentials credential) {
    credential.setPassword(encryptionService.decryptValue(credential.getPassword(),
                                                          credential.getKey()));
    return credential;
  }

  public void addCredential(Credentials credential, int userid) {
    credentialsMapper.insertCredentials(encryptPassword(credential), userid);
  }

  private Credentials encryptPassword(Credentials credential) {
    String key = RandomStringUtils.random(16, true, true);
    credential.setKey(key);
    credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
    return credential;
  }

  public void updateCredential(Credentials credential) {
    credentialsMapper.updateCredentials(encryptPassword(credential));
  }

  public void deleteCredential(int credentialid) {
    credentialsMapper.deleteCredentials(credentialid);
  }

}
