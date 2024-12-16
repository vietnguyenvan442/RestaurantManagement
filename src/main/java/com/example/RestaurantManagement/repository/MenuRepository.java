package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu findById(int id);

    @Query("SELECT m FROM Menu m WHERE m.state = true")
    Menu findByStateTrue();

    Menu findByName(String name);
}
