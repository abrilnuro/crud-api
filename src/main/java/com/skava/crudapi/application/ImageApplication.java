package com.skava.crudapi.application;

import com.skava.crudapi.document.Image;
import com.skava.crudapi.repository.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageApplication {

    @Autowired
    ImageRepository imageRepository;

    public String saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setTitle(file.getName());
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        image = imageRepository.insert(image);

        return image.getId();
    }

    public Image getPhoto(String id) {
        return imageRepository.findById(id).get();
    }
}
