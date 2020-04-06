package com.udacity.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Files {
    private int fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private byte[] filedata;
}
