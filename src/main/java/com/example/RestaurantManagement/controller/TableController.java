package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.Time;
import com.example.RestaurantManagement.entity.Table;
import com.example.RestaurantManagement.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tables")
public class TableController {
    @Autowired
    private TableService tableService;

    @PostMapping("/available")
    public ResponseEntity<List<Table>> get(@RequestBody Time time){
        return ResponseEntity.ok(tableService.getEmptyTables(time.getStartTime(), time.getEndTime()));
    }
}
