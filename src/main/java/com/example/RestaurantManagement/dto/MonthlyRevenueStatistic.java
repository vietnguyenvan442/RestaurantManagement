package com.example.RestaurantManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyRevenueStatistic {
    private int month;
    private double totalRevenue; // Tổng doanh thu bán ra
    private double totalImport;  // Tổng tiền nhập nguyên liệu

    public MonthlyRevenueStatistic(int month, double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }
}
