package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.FilesMapper;
import com.udacity.cloudstorage.models.Files;
import com.udacity.cloudstorage.models.ResponseFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {

  @Autowired
  private FilesMapper filesMapper;

  public List<ResponseFile> getAllFiles(int userid) throws Exception {
    List<Files> files = filesMapper.findByUserId(userid);
    if (files == null) {
      throw new Exception();
    }
    return files.stream().map(this::getResponseFile).collect(Collectors.toList());
  }

  public ResponseFile getResponseFile(Files file) {
    String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
    String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
    return ResponseFile.builder()
        .fileid(file.getFileid())
        .filename(file.getFilename())
        .dataURL(dataURL)
        .build();
  }

  public void addFile(MultipartFile fileUpload, int userid) throws IOException {
    Files file = new Files();
    try {
      file.setContenttype(fileUpload.getContentType());
      file.setFiledata(fileUpload.getBytes());
      file.setFilename(fileUpload.getOriginalFilename());
      file.setFilesize(Long.toString(fileUpload.getSize()));
    } catch (IOException e) {
      throw e;
    }
    filesMapper.insertFile(file, userid);
  }

  public void deleteFile(int fileid) {
    filesMapper.deleteFile(fileid);
  }

}
