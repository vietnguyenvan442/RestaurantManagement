package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Outbound_Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundReceiptRepository extends JpaRepository<Outbound_Receipt, Integer> {
}
