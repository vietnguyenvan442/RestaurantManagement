package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    public Supplier findById(int id);

    Supplier findByEmail(String email);

    Supplier findByPhoneNumber(String phoneNumber);
}
