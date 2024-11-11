package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    Dish findById(int id);
}
