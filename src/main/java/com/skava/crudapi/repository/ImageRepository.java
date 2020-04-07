package com.skava.crudapi.repository;

import com.skava.crudapi.document.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {
}
