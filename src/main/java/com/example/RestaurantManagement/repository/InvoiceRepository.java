package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.end_date >= CURRENT_DATE")
    List<Invoice> findAllNotExpired();
}
