package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {
    Combo findById(int id);

    Combo findByName(String name);
}
