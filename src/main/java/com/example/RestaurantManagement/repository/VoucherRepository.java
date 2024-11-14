package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    Voucher findById(int id);
}
