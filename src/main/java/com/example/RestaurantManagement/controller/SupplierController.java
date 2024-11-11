package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Supplier;
import com.example.RestaurantManagement.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin
@Slf4j
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("")
    public ResponseEntity<List<Supplier>> getAll() {
        return ResponseEntity.of(Optional.ofNullable(supplierService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable int id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }


    @PostMapping("")
    public ResponseEntity<Supplier> add(@RequestBody Supplier supplier) {
        log.info("Adding supplier: {}", supplier.getName());
        return ResponseEntity.ok(supplierService.add(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable int id, @RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.update(id, supplier));
    }
}
