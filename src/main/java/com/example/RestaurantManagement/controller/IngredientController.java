package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.entity.Ingredient;
import com.example.RestaurantManagement.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
@Slf4j
@CrossOrigin
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.of(Optional.ofNullable(ingredientService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        log.info("Adding ingredient: {}", ingredient.getName());
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.update(id, ingredient));
    }
}
