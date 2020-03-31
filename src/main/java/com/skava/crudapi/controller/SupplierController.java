package com.skava.crudapi.controller;

import com.skava.crudapi.SupplierApplication.SupplierApplication;
import com.skava.crudapi.document.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
public class SupplierController {

    @Autowired
    SupplierApplication supplierApplication;

    @PostMapping
    public Supplier save(@RequestBody Supplier supplier) {
        return this.supplierApplication.save(supplier);
    }

    @GetMapping
    public List<Supplier> findAll() {
        return this.supplierApplication.findAll();
    }

    @GetMapping("{id}")
    public Supplier findById(@PathVariable  String id) {
        return this.supplierApplication.findById(id);
    }

    @PatchMapping
    public Supplier updateById(@RequestBody Supplier supplier) {
        return this.supplierApplication.updateById(supplier);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        this.supplierApplication.deleteById(id);
    }
}
