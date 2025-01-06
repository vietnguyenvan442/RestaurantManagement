package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.service.BillService;
import com.example.RestaurantManagement.service.InboundReceiptService;
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

    @Autowired
    private InboundReceiptService inboundReceiptService;

    @GetMapping("/revenue/{year}")
    public ResponseEntity<List<MonthlyRevenueStatistic>> getMonthlyRevenueStatistic(@PathVariable int year){
        return ResponseEntity.ok(billService.getMonthlyRevenueStatistic(year));
    }

    @GetMapping("/revenue/{year}/bills/{month}")
    public ResponseEntity<List<Bill>> getBillStatByMonth(@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(billService.getBillsByMonth(month, year));
    }

    @GetMapping("/revenue/{year}/inbound/{month}")
    public ResponseEntity<List<Inbound_Receipt>> getInboundsByMonth(@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(inboundReceiptService.getStatByMonth(month, year));
    }

    @GetMapping("/top-dishes")
    public List<TopDishStatisticOutput> getTopDishesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return billService.getTopDishesByDateRange(startDate, endDate);
    }

}
