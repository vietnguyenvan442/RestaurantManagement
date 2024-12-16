package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Dish;
import com.example.RestaurantManagement.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dishes")
@CrossOrigin
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("")
    public ResponseEntity<List<Dish>> getAll() {
        return ResponseEntity.of(Optional.ofNullable(dishService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getById(@PathVariable int id) {
        return ResponseEntity.ok(dishService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Dish> add(@RequestBody Dish dish) {
        log.info("Adding dish: {}", dish.getName());
        return ResponseEntity.ok(dishService.add(dish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> update(@PathVariable int id, @RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.update(id, dish));
    }
}
