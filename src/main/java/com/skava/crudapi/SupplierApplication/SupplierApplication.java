package com.skava.crudapi.SupplierApplication;

import com.skava.crudapi.document.Supplier;
import com.skava.crudapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Component
public class SupplierApplication {

    @Autowired
    SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    public List<Supplier> findAll() {
        return this.supplierRepository.findAll();
    }

    public Supplier findById(String id) {
        Optional<Supplier> optional = this.supplierRepository.findById(id);
        Assert.isTrue(optional.isPresent(), "Can't find supplier with id: " + id);
        return optional.get();
    }

    public Supplier updateById(Supplier supplier) {
        boolean exists = this.supplierRepository.existsById(supplier.getId());
        Assert.isTrue(exists, "Doesn´t exist supplier with id: " + supplier.getId());
        return this.supplierRepository.save(supplier);
    }

    public void deleteById(String id) {
        boolean exists = this.supplierRepository.existsById(id);
        Assert.isTrue(exists, "Doesn´t exist supplier with id: " + id);
        this.supplierRepository.deleteById(id);
    }
}
