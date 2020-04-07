package com.skava.crudapi.repository;

import com.skava.crudapi.document.SupplierDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<SupplierDocument, String> {

}
