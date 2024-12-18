package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.dto.BillDto;
import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.entity.Detail_Bill;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.BillRepository;
import com.example.RestaurantManagement.repository.DetailBillRepository;
import com.example.RestaurantManagement.repository.VoucherRepository;
import com.example.RestaurantManagement.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private DetailBillRepository detailBillRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TableService tableService;

    @Autowired
    private DishService dishService;

    @Autowired
    private ComboSerivce comboSerivce;

    @Autowired
    private UserService userService;

    @Override
    public Bill add(Bill bill) {
        if (bill.getCustomer() != null) bill.setCustomer(customerService.getById(bill.getCustomer().getId()));
        bill.setTable(tableService.getById(bill.getTable().getId()));

        if (bill.getStart() == null || bill.getEnd() == null) throw new ValidationException("Start & End time not null");

        for (Detail_Bill db: bill.getDetail_bills()){
            if (db.getDish() != null) {
                db.setDish(dishService.getById(db.getDish().getId()));
                db.setPrice(db.getDish().getPrice());
            }
            if (db.getCombo() != null) {
                db.setCombo(comboSerivce.getById(db.getCombo().getId()));
                db.setPrice(db.getCombo().getPrice());
            }
            db.setTotal(db.getAmount() * db.getPrice());
            db.setBill(bill);
        }

        return billRepository.save(bill);
    }

    @Override
    public Bill update(int id, Bill bill) {
        Bill old = billRepository.findById(id);

        old.setTable(tableService.getById(bill.getTable().getId()));

        if (bill.getStart() == null || bill.getEnd() == null) throw new ValidationException("Start & End time not null");

        for (Detail_Bill db: bill.getDetail_bills()){
            if (db.getDish() != null) {
                db.setDish(dishService.getById(db.getDish().getId()));
                db.setPrice(db.getDish().getPrice());
            }
            if (db.getCombo() != null) {
                db.setCombo(comboSerivce.getById(db.getCombo().getId()));
                db.setPrice(db.getCombo().getPrice());
            }
            db.setBill(bill);
        }

        old.setDetail_bills(bill.getDetail_bills());
        old.setStart(bill.getStart());
        old.setEnd(bill.getEnd());
        old.setTotal(bill.getTotal());
        old.setDes(bill.getDes());
        old.setState(bill.isState());

        return billRepository.save(bill);
    }

    @Override
    public Bill bookTable(Bill bill) {
        if (bill.getCustomer() != null) bill.setCustomer(customerService.getById(bill.getCustomer().getId()));

        bill.setTable(tableService.getById(bill.getTable().getId()));

        if (bill.getStart() == null || bill.getEnd() == null) throw new ValidationException("Start & End time not null");

        return billRepository.save(bill);
    }

    @Override
    public Bill bookDish(Bill bill) {
        Bill old = billRepository.findById(bill.getId());

        for (Detail_Bill db: bill.getDetail_bills()){
            if (db.getDish() != null) {
                db.setDish(dishService.getById(db.getDish().getId()));
                db.setPrice(db.getDish().getPrice());
            }
            if (db.getCombo() != null) {
                db.setCombo(comboSerivce.getById(db.getCombo().getId()));
                db.setPrice(db.getCombo().getPrice());
            }
            db.setTotal(db.getAmount() * db.getPrice());
            db.setBill(old);
        }
        old.setDetail_bills(bill.getDetail_bills());
        return billRepository.save(old);
    }

    @Override
    public Bill getBillByTableAndTime(BillDto billDto) {
        Bill bill = billRepository.findByTableIdAndDate(billDto.getId_table(), billDto.getDate());

        double price = 0;
        for (Detail_Bill db: bill.getDetail_bills()){
            price += db.getTotal();
        }
        bill.setTotal(price + bill.getTable().getPrice());
        if (bill.getVoucher() != null){
            bill.setVoucher(voucherRepository.findById(bill.getVoucher().getId()));
            bill.setTotal(bill.getTotal() * (1 - bill.getVoucher().getValue()));
        }
        bill.setState(false);
        return billRepository.save(bill);
    }

    @Override
    public Bill pay(int bill_id, Bill bill) {
        Bill old = billRepository.findById(bill_id);
        old.setSale_staff(userService.getSaleStaffById(bill.getSale_staff().getId()));
        old.setState(true);
        return billRepository.save(old);
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill getById(int id) {
        Bill bill = billRepository.findById(id);
        if (bill == null) throw new ResourceNotFoundException("Bill not found");
        return bill;
    }

    @Override
    public List<Bill> getAllByCus(int id_cus) {
        return billRepository.findAllByCustomerId(id_cus);
    }

    @Override
    public List<MonthlyRevenueStatistic> getMonthlyRevenueStatistic(int year) {
        List<MonthlyRevenueStatistic> revenueStatistics = billRepository.findMonthlyRevenueStatistics(year);
        Map<Integer, Double> revenueByMonth = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            revenueByMonth.put(month, 0.0);
        }
        for (MonthlyRevenueStatistic stat : revenueStatistics) {
            revenueByMonth.put(stat.getMonth(), stat.getTotal());
        }
        List<MonthlyRevenueStatistic> result = revenueByMonth.entrySet().stream()
                .map(entry -> new MonthlyRevenueStatistic(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<TopDishStatisticOutput> getTopDishesByDateRange(LocalDate startDate, LocalDate endDate) {
        return detailBillRepository.findTopDishesByDateRange(startDate, endDate);
    }
}
