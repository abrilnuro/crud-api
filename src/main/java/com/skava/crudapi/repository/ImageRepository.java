package com.skava.crudapi.repository;

import com.skava.crudapi.document.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
