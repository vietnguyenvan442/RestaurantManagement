package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Detail_Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DetailBillRepository extends JpaRepository<Detail_Bill, Integer> {
    @Query("SELECT new com.example.RestaurantManagement.dto.TopDishStatisticOutput(d.dish.name, SUM(d.amount)) " +
            "FROM Detail_Bill d " +
            "JOIN d.bill b " +
            "WHERE DATE(b.start) >= :startDate AND DATE(b.end) <= :endDate " +
            "GROUP BY d.dish.name " +
            "ORDER BY SUM(d.amount) DESC")
    List<TopDishStatisticOutput> findTopDishesByDateRange(@Param("startDate") LocalDate startDate,
                                                          @Param("endDate") LocalDate endDate);
}
