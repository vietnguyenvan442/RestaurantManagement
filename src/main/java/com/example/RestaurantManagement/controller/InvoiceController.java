package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Invoice;
import com.example.RestaurantManagement.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
@CrossOrigin
@Slf4j
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAll(){
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable int id){
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @GetMapping("/not-expired")
    public ResponseEntity<List<Invoice>> getAllNotExpired() {
        return ResponseEntity.of(Optional.ofNullable(invoiceService.getAllNotExpired()));
    }

    @PostMapping("")
    public ResponseEntity<Invoice> add(@RequestBody Invoice invoice) {
        log.info("Adding invoice");
        return ResponseEntity.ok(invoiceService.add(invoice));
    }
}