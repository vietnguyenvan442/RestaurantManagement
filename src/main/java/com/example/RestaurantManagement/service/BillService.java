package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.entity.Detail_Bill;
import com.example.RestaurantManagement.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface BillService {

    public Bill add(Bill bill);

    public Bill update(int id, Bill bill);

    public Bill bookTable(Bill bill);

    public Bill bookDish(Bill bill);

    public List<Bill> getBillByTableAndTime(int id_table, LocalDate date);

    public Detail_Bill delivered(int id, Detail_Bill detail_bill);

    public Bill pay(int bill_id, User user);

    public List<Bill> getAll();

    public List<Bill> getUnpaids();

    public Bill getById(int id);

    public List<Bill> getAllByCus(int id_cus);

    public List<MonthlyRevenueStatistic> getMonthlyRevenueStatistic(int year);

    public List<Bill> getBillsByMonth(int month, int year);

    public List<TopDishStatisticOutput> getTopDishesByDateRange(LocalDate startDate, LocalDate endDate);
}
