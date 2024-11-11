package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findById(int id);

    Ingredient findByName(String name);
}
