package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.BillDto;
import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticInput;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;

import java.util.List;

public interface BillService {
    public Bill bookTable(Bill bill);

    public Bill bookDish(Bill bill);

    public Bill getBillByTableAndTime(BillDto billDto);

    public Bill pay(int bill_id, Bill bill);

    public List<Bill> getAll();

    public Bill getById(int id);

    public List<Bill> getAllByCus(int id_cus);

    public List<MonthlyRevenueStatistic> getMonthlyRevenueStatistic(int year);

    public List<TopDishStatisticOutput> getTopDishesByDateRange(TopDishStatisticInput topDishStatisticInput);
}
