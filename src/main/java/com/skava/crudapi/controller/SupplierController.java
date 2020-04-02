package com.skava.crudapi.controller;

import com.skava.crudapi.SupplierApplication.SupplierApplication;
import com.skava.crudapi.document.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false",
        methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping("/api")
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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity  uploadFile(@RequestParam MultipartFile file) {
        String name = file.getName();
        return ResponseEntity.ok().build();
    }
}
