package com.skava.crudapi.application;

import com.skava.crudapi.document.SupplierDocument;
import com.skava.crudapi.dto.ImageDto;
import com.skava.crudapi.dto.SupplierDto;
import com.skava.crudapi.repository.SupplierRepository;
import org.springframework.beans.BeanUtils;
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
        BeanUtils.copyProperties(supplierDto, supplierDocument, "image");

        Optional<SupplierDocument> supplierSaved = Optional.ofNullable(this.supplierRepository.save(supplierDocument));
        Assert.isTrue(supplierSaved.isPresent(), "A problem occurred while saving supplier");

        String supplierId = supplierSaved.get().getId();
        supplierDto.setId(supplierId);

        ImageDto imageDto = supplierDto.getImage();
        imageDto.setIdSupplier(supplierId);
        ImageDto imageSaved = this.imageApplication.saveAsBase64(imageDto);

        supplierDto.setImage(imageSaved);

        return supplierDto;
    }

    public List<SupplierDocument> findAll() {
        return this.supplierRepository.findAll();
    }

    public SupplierDto findById(String id) {
        Optional<SupplierDocument> supplierFinded = this.supplierRepository.findById(id);
        Assert.isTrue(supplierFinded.isPresent(), "An error occurred while searching " +
                "for the provider with id: " + id);

        ImageDto imageDto = this.imageApplication.findBySupplierId(id);

        SupplierDto supplierDto = new SupplierDto();
        BeanUtils.copyProperties(supplierFinded.get(), supplierDto);
        supplierDto.setImage(imageDto);

        return supplierDto;
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
