package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/top-dishes")
    public List<TopDishStatisticOutput> getTopDishesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return billService.getTopDishesByDateRange(startDate, endDate);
    }

}
