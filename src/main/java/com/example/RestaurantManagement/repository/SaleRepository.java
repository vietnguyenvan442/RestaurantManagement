package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Role;
import com.example.RestaurantManagement.entity.Sale_Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale_Staff, Integer> {

}
