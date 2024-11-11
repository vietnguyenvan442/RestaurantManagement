package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu findById(int id);

    Menu findByName(String name);
}
