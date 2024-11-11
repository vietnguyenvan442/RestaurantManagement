package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Detail_Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detail_DishRepository extends JpaRepository<Detail_Dish, Integer> {
}
