package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.service.InboundReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/inbound-receipts")
public class InboundReceiptController {
    @Autowired
    private InboundReceiptService inboundReceiptService;

    @GetMapping("")
    public ResponseEntity<List<Inbound_Receipt>> getAll(){
        return ResponseEntity.ok(inboundReceiptService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<Inbound_Receipt> add(@RequestBody Inbound_Receipt inbound_receipt){
        return ResponseEntity.ok(inboundReceiptService.add(inbound_receipt));
    }
}
