package ru.itis.companies.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.companies.dto.CompaniesDto;
import ru.itis.companies.models.Company;
import ru.itis.companies.services.CompaniesService;
//import ru.itpark.app.dto.ProductDto;
//import ru.itpark.app.models.Product;
//import ru.itpark.app.models.User;
//import ru.itpark.app.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class ProductsController {

    @Autowired
    private CompaniesService service;

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

//    @PostMapping
//    public ResponseEntity<Company> addProduct(@RequestBody CompaniesDto form) {
//        return ResponseEntity.status(201).body(service.add(form));
//    }

    @GetMapping
    public ResponseEntity<List<Company>> getProducts() {
        List<Company> companies = service.getAll();
        return ResponseEntity.ok(companies);
    }

//    @GetMapping("/{product-id}")
//    public ResponseEntity<Company> getProduct(@PathVariable("product-id") Long id) {
//        return ResponseEntity.ok(service.get(id));
//    }
}
