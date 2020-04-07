package com.skava.crudapi.application;

import com.skava.crudapi.document.ImageDocument;
import com.skava.crudapi.dto.ImageDto;
import com.skava.crudapi.repository.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class ImageApplication {

    @Autowired
    ImageRepository imageRepository;

    public String save(MultipartFile file) throws IOException {
        ImageDocument imageDocument = new ImageDocument();
        imageDocument.setTitle(file.getName());
        imageDocument.setImageBinary(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        Optional<ImageDocument> imageSaved = Optional.ofNullable(imageRepository.insert(imageDocument));
        Assert.isTrue(imageSaved.isPresent(), "A problem occurred while saving image");

        return imageDocument.getId();
    }

    public ImageDto saveAsBase64(ImageDto imageDto){
        ImageDocument imageDocument = new ImageDocument();
        imageDocument.setTitle(imageDto.getTitle());
        imageDocument.setImage(imageDto.getImage());

        Optional<ImageDocument> imageSaved = Optional.ofNullable(imageRepository.save(imageDocument));
        Assert.isTrue(imageSaved.isPresent(), "A problem occurred while saving image");

        imageDto.setId(imageSaved.get().getId());

        return imageDto;
    }

    /*public ImageDocument findById(String id) {
        return imageRepository.findById(id).get();
    }*/
}
