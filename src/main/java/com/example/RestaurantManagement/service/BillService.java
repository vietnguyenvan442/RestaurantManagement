package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.BillDto;
import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.entity.Sale_Staff;
import com.example.RestaurantManagement.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface BillService {

    public Bill add(Bill bill);

    public Bill update(int id, Bill bill);

    public Bill bookTable(Bill bill);

    public Bill bookDish(Bill bill);

    public Bill getBillByTableAndTime(BillDto billDto);

    public Bill pay(int bill_id, User user);

    public List<Bill> getAll();

    public List<Bill> getUnpaids();

    public Bill getById(int id);

    public List<Bill> getAllByCus(int id_cus);

    public List<MonthlyRevenueStatistic> getMonthlyRevenueStatistic(int year);

    public List<TopDishStatisticOutput> getTopDishesByDateRange(LocalDate startDate, LocalDate endDate);
}
