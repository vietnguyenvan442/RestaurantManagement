package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.entity.Outbound_Receipt;
import com.example.RestaurantManagement.service.InboundReceiptService;
import com.example.RestaurantManagement.service.OutboundReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/outbound-receipts")
public class OutboundReceiptController {
    @Autowired
    private OutboundReceiptService outboundReceiptService;

    @GetMapping("")
    public ResponseEntity<List<Outbound_Receipt>> getAll(){
        return ResponseEntity.ok(outboundReceiptService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<Outbound_Receipt> add(@RequestBody Outbound_Receipt outbound_receipt){
        return ResponseEntity.ok(outboundReceiptService.add(outbound_receipt));
    }
}