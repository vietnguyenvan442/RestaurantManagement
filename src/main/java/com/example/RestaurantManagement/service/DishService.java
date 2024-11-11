package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Dish;

import java.util.List;

public interface DishService {
    public List<Dish> getAll();

    public Dish getById(int id);

    public Dish add(Dish dish);

    public Dish update(int id, Dish dish);
}
