package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticInput;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private BillService billService;

    @GetMapping("/revenue/{year}")
    public ResponseEntity<List<MonthlyRevenueStatistic>> getMonthlyRevenueStatistic(@PathVariable int year){
        return ResponseEntity.ok(billService.getMonthlyRevenueStatistic(year));
    }

    @GetMapping("/top-dish")
    public ResponseEntity<List<TopDishStatisticOutput>> getTopDish(@RequestBody TopDishStatisticInput topDishStatisticInput){
        return ResponseEntity.ok(billService.getTopDishesByDateRange(topDishStatisticInput));
    }
}
