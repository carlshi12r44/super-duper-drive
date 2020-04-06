package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.FilesMapper;
import com.udacity.cloudstorage.models.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    @Autowired
    private FilesMapper filesMapper;

    public List<Files> getAllFiles(int userid) throws Exception {
        List<Files> files = filesMapper.findByUserId(userid);
        if (files == null) {
            throw new Exception();
        }
        return files;
    }

    public void addFile(Files file, int userid) {
        filesMapper.insertFile(file, userid);
    }

    public void updateFile(Files file) {
        filesMapper.updateFile(file);
    }

    public void deleteFile(int fileid) {
        filesMapper.deleteFile(fileid);
    }

}
