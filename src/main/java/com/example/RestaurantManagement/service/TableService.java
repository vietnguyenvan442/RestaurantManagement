package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Table;

import java.time.LocalDateTime;
import java.util.List;

public interface TableService {
    Table getById(int id);

    List<Table> getEmptyTables(LocalDateTime startTime, LocalDateTime endTime);

}
