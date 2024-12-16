package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Table;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.repository.TableRepository;
import com.example.RestaurantManagement.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableRepository tableRepository;

    @Override
    public Table getById(int id) {
        Table table = tableRepository.findById(id);
        if (table == null) throw new ResourceNotFoundException("Table not found");
        return table;
    }

    @Override
    public List<Table> getEmptyTables(LocalDateTime startTime, LocalDateTime endTime) {
        return tableRepository.findEmptyTablesWithinTime(startTime, endTime);
    }

}
