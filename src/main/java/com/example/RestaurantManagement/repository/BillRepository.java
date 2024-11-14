package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findById(int id);

    @Query("SELECT b FROM Bill b WHERE b.table.id = :id AND b.start <= :date AND b.end >= :date")
    Bill findByTableIdAndDate(@Param("id") int id, @Param("date") LocalDateTime date);

    @Query("SELECT b FROM Bill b WHERE b.customer.id = :id_cus")
    List<Bill> findAllByCustomerId(@Param("id_cus") int id_cus);

    @Query("SELECT new com.example.RestaurantManagement.dto.MonthlyRevenueStatistic(" +
            "MONTH(b.start), SUM(b.total)) " +
            "FROM Bill b " +
            "WHERE b.state = true AND YEAR(b.start) = :year " +
            "GROUP BY MONTH(b.start) " +
            "ORDER BY MONTH(b.start)")
    List<MonthlyRevenueStatistic> findMonthlyRevenueStatistics(@Param("year") int year);
}
