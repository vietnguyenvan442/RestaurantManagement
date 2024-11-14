package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    Table findById(int id);
}
