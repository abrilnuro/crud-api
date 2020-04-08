package com.skava.crudapi.repository;

import com.skava.crudapi.document.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {

    ImageDocument findByIdSupplier(@Param("idSupplier") String idSupplier);
}
