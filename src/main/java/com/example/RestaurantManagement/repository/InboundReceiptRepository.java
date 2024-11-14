package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundReceiptRepository extends JpaRepository<Inbound_Receipt, Integer> {
}
