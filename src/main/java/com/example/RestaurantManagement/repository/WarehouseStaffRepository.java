package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Warehouse_Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseStaffRepository extends JpaRepository<Warehouse_Staff, Integer> {
    Warehouse_Staff findById(int id);
}
