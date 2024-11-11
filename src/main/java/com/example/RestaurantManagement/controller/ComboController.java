package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Combo;
import com.example.RestaurantManagement.service.ComboSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/combos")
@CrossOrigin
@Slf4j
public class ComboController {
    @Autowired
    private ComboSerivce comboSerivce;

    @GetMapping("")
    public ResponseEntity<List<Combo>> getAll() {
        return ResponseEntity.of(Optional.ofNullable(comboSerivce.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Combo> getById(@PathVariable int id) {
        return ResponseEntity.ok(comboSerivce.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Combo> add(@RequestBody Combo combo) {
        log.info("Adding combo: {}", combo.getName());
        return ResponseEntity.ok(comboSerivce.add(combo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Combo> update(@PathVariable int id, @RequestBody Combo combo) {
        return ResponseEntity.ok(comboSerivce.update(id, combo));
    }
}
