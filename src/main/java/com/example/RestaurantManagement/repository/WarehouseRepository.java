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
            "COALESCE(SUM(din.amount), 0) - " +
            "COALESCE((SELECT SUM(d.amount) FROM Detail_Outbound d WHERE d.ingredient.id = i.id), 0), " +
            "(COALESCE(SUM(din.amount), 0) - " +
            "COALESCE((SELECT SUM(d.amount) FROM Detail_Outbound d WHERE d.ingredient.id = i.id), 0)) * i.price, " +
            "i) " +
            "FROM Detail_Inbound din " +
            "JOIN din.ingredient i " +
            "GROUP BY i.id, i.price, i.name")
    List<Detail_Warehouse> calculateCurrentInventory();
}
