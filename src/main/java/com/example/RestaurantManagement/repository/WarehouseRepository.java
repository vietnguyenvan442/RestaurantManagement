package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Detail_Warehouse;
import com.example.RestaurantManagement.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findById(int id);

    @Query("SELECT new com.example.RestaurantManagement.entity.Detail_Warehouse(" +
            "(COALESCE(SUM(din.amount), 0) - COALESCE(SUM(dout.amount), 0)), " +
            "din.unit, " +
            "din.price, " +
            "(COALESCE(SUM(din.amount), 0) - COALESCE(SUM(dout.amount), 0)) * din.price, " +
            "i) " +
            "FROM Detail_Inbound din " +
            "LEFT JOIN din.ingredient i " +
            "LEFT JOIN Detail_Outbound dout ON dout.ingredient.id = i.id " +
            "GROUP BY i.id, din.unit, din.price")
    List<Detail_Warehouse> calculateCurrentInventory();
}
