package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    Table findById(int id);

    @Query("""
        SELECT t 
        FROM Table t 
        WHERE NOT EXISTS (
            SELECT b 
            FROM Bill b 
            WHERE b.table = t 
              AND ((b.start BETWEEN :startTime AND :endTime) OR (b.end BETWEEN :startTime AND :endTime) OR (:startTime BETWEEN b.start AND b.end))
        )
    """)
    List<Table> findEmptyTablesWithinTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);


}
