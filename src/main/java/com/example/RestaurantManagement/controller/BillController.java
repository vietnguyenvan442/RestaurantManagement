package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.BillDto;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/book-table")
    public ResponseEntity<Bill> bookTable(@RequestBody Bill bill){
        return ResponseEntity.ok(billService.bookTable(bill));
    }

    @PostMapping("/book-dish")
    public ResponseEntity<Bill> bookDish(@RequestBody Bill bill){
        return ResponseEntity.ok(billService.bookDish(bill));
    }

    @GetMapping("/search")
    public ResponseEntity<Bill> getBillByTableAndTime(@RequestBody BillDto billDto){
        return ResponseEntity.ok(billService.getBillByTableAndTime(billDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> pay(@PathVariable int id, @RequestBody Bill bill){
        return ResponseEntity.ok(billService.pay(id, bill));
    }

    @GetMapping("")
    public ResponseEntity<List<Bill>> getAll(){
        return ResponseEntity.ok(billService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getById(@PathVariable int id){
        return ResponseEntity.ok(billService.getById(id));
    }

    @GetMapping("customer/{id_cus}")
    public ResponseEntity<List<Bill>> getBillByCustomer(@PathVariable int id_cus){
        return ResponseEntity.ok(billService.getAllByCus(id_cus));
    }
}
