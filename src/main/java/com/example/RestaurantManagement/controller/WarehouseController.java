package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.entity.Outbound_Receipt;
import com.example.RestaurantManagement.entity.Warehouse;
import com.example.RestaurantManagement.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin
@Slf4j
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/inbound")
    public ResponseEntity<Inbound_Receipt> inboundWarehouse(@RequestBody Inbound_Receipt inbound_receipt){
        return ResponseEntity.ok(warehouseService.inboundWarehouse(inbound_receipt));
    }

    @PostMapping("/outbound")
    public ResponseEntity<Outbound_Receipt> outboundWarehouse(@RequestBody Outbound_Receipt outbound_receipt){
        return ResponseEntity.ok(warehouseService.outboundWarehouse(outbound_receipt));
    }

    @GetMapping("")
    public ResponseEntity<Warehouse> getWarehouse(){
        return ResponseEntity.ok(warehouseService.getWarehouse());
    }
}
