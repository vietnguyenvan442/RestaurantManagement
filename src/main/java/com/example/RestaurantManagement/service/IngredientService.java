package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    public List<Ingredient> getAll();
    public Ingredient getById(int id);
    public Ingredient add(Ingredient ingredient);
    public Ingredient update(int id, Ingredient ingredient);
}
