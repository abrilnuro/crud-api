package com.skava.crudapi.document;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;

@Document(collection = "image")
public class ImageDocument {

    @Id
    private String id;

    private String idSupplier;

    private String title;

    private String image;

    private Binary imageBinary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Binary getImageBinary() {
        return imageBinary;
    }

    public void setImageBinary(Binary imageBinary) {
        this.imageBinary = imageBinary;
    }
}
