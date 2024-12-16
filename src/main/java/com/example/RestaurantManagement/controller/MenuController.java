package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Menu;
import com.example.RestaurantManagement.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
@CrossOrigin
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public ResponseEntity<List<Menu>> getAll() {
        return ResponseEntity.of(Optional.ofNullable(menuService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getById(@PathVariable int id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<Menu> getByActive() {
        return ResponseEntity.ok(menuService.getByActive());
    }

    @PostMapping("")
    public ResponseEntity<Menu> add(@RequestBody Menu menu) {
        log.info("Adding combo: {}", menu.getName());
        return ResponseEntity.ok(menuService.add(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> update(@PathVariable int id, @RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.update(id, menu));
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<String> active(@PathVariable int id) {
        menuService.active(id);
        return ResponseEntity.ok("Success");
    }
}
