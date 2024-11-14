package com.example.RestaurantManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyRevenueStatistic {
    private int month;
    private double total;
}
