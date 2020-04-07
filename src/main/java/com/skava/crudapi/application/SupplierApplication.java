package com.skava.crudapi.application;

import com.skava.crudapi.document.SupplierDocument;
import com.skava.crudapi.dto.ImageDto;
import com.skava.crudapi.dto.SupplierDto;
import com.skava.crudapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierApplication {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ImageApplication imageApplication;

    public SupplierDto save(SupplierDto supplierDto) throws IOException {
        SupplierDocument supplierDocument = new SupplierDocument();
        supplierDocument.setName(supplierDto.getName());
        supplierDocument.setDescription(supplierDto.getDescription());
        supplierDocument.setPhoneNumber(supplierDto.getPhoneNumber());

        Optional<SupplierDocument> supplierSaved = Optional.ofNullable(this.supplierRepository.save(supplierDocument));
        Assert.isTrue(supplierSaved.isPresent(), "A problem occurred while saving supplier");

        String supplierId = supplierSaved.get().getId();
        supplierDto.setId(supplierId);

        ImageDto imageDto = this.imageApplication.saveAsBase64(supplierDto.getImage());

        imageDto.setIdSupplier(supplierId);
        supplierDto.setImage(imageDto);

        return supplierDto;
    }

    public List<SupplierDocument> findAll() {
        return this.supplierRepository.findAll();
    }

    public SupplierDocument findById(String id) {
        Optional<SupplierDocument> optional = this.supplierRepository.findById(id);
        Assert.isTrue(optional.isPresent(), "Can't find supplier with id: " + id);
        return optional.get();
    }

    public SupplierDocument updateById(SupplierDocument supplierDocument) {
        boolean exists = this.supplierRepository.existsById(supplierDocument.getId());
        Assert.isTrue(exists, "Doesn´t exist supplierDocument with id: " + supplierDocument.getId());
        return this.supplierRepository.save(supplierDocument);
    }

    public void deleteById(String id) {
        boolean exists = this.supplierRepository.existsById(id);
        Assert.isTrue(exists, "Doesn´t exist supplier with id: " + id);
        this.supplierRepository.deleteById(id);
    }
}
