package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Sale_Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleStaffRepository extends JpaRepository<Sale_Staff, Integer> {
    Sale_Staff findById(int id);
}
