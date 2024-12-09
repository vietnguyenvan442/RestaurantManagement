package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.Detail_Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailWarehouseRepository extends JpaRepository<Detail_Warehouse, Integer> {
    @Modifying
    @Query("DELETE FROM Detail_Warehouse d")
    void deleteAllDetailWarehouse();

}
