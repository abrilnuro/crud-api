package com.skava.crudapi.controller;

import com.skava.crudapi.application.ImageApplication;
import com.skava.crudapi.application.SupplierApplication;
import com.skava.crudapi.document.SupplierDocument;
import com.skava.crudapi.dto.ImageDto;
import com.skava.crudapi.dto.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false",
        methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    SupplierApplication supplierApplication;

    @Autowired
    ImageApplication imageApplication;

    @PostMapping
    public SupplierDto save(@RequestBody SupplierDto supplierDto) throws IOException {
        return this.supplierApplication.save(supplierDto);
    }

    @GetMapping
    public List<SupplierDocument> findAll() {
        return this.supplierApplication.findAll();
    }

    @GetMapping("{id}")
    public SupplierDto findById(@PathVariable String id) {
        return this.supplierApplication.findById(id);
    }

    @PatchMapping
    public SupplierDocument updateById(@RequestBody SupplierDocument supplierDocument) {
        return this.supplierApplication.updateById(supplierDocument);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        this.supplierApplication.deleteById(id);
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String  uploadImage(@RequestParam MultipartFile file) throws IOException {
        return this.imageApplication.save(file);
    }

    @GetMapping("/image/{id}")
    public ImageDto getImage(@PathVariable String id) {
        return imageApplication.findById(id);
    }
}
