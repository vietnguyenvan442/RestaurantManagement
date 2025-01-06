package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundReceiptRepository extends JpaRepository<Inbound_Receipt, Integer> {

    @Query("SELECT i FROM Inbound_Receipt i WHERE FUNCTION('MONTH', i.date) = :month AND FUNCTION('YEAR', i.date) = :year")
    List<Inbound_Receipt> findBillsByMonth(@Param("month") int month, @Param("year") int year);

}
