package com.skava.crudapi.document;

import org.springframework.web.multipart.MultipartFile;

public class Image {

    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
