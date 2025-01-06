package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.dto.MonthlyRevenueStatistic;
import com.example.RestaurantManagement.dto.TopDishStatisticOutput;
import com.example.RestaurantManagement.entity.Bill;
import com.example.RestaurantManagement.entity.Detail_Bill;
import com.example.RestaurantManagement.entity.User;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.BillRepository;
import com.example.RestaurantManagement.repository.DetailBillRepository;
import com.example.RestaurantManagement.repository.VoucherRepository;
import com.example.RestaurantManagement.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

        if (bill.getStart() == null || bill.getEnd() == null)
            throw new ValidationException("Start & End time not null");

        for (Detail_Bill db : bill.getDetail_bills()) {
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

        if (bill.getStart() == null || bill.getEnd() == null)
            throw new ValidationException("Start & End time not null");

        for (Detail_Bill db : bill.getDetail_bills()) {
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

        if (bill.getStart() == null || bill.getEnd() == null)
            throw new ValidationException("Start & End time not null");

        log.info("Success");
        return billRepository.save(bill);
    }

    @Override
    public Bill bookDish(Bill bill) {
        // Lấy hóa đơn cũ từ cơ sở dữ liệu
        Bill old = billRepository.findById(bill.getId());
        if (old == null) {
            throw new IllegalArgumentException("Bill not found with id: " + bill.getId());
        }

        double totalPrice = 0;

        // Xóa các chi tiết cũ và thêm mới
        old.getDetail_bills().clear();

        for (Detail_Bill db : bill.getDetail_bills()) {
            if (db.getId() != 0) {
                // Tìm detail_bill đã tồn tại trong cơ sở dữ liệu
                Detail_Bill existingDetail = detailBillRepository.findById(db.getId());
                if (existingDetail == null) {
                    throw new IllegalArgumentException("Detail_Bill not found with id: " + db.getId());
                }
                existingDetail.setAmount(db.getAmount());
                db = existingDetail;
            } else {
                // Xử lý nếu là chi tiết mới
                if (db.getDish() != null) {
                    db.setDish(dishService.getById(db.getDish().getId()));
                    db.setPrice(db.getDish().getPrice());
                }
                if (db.getCombo() != null) {
                    db.setCombo(comboSerivce.getById(db.getCombo().getId()));
                    db.setPrice(db.getCombo().getPrice());
                }
            }

            // Tính tổng tiền và gắn chi tiết vào hóa đơn
            db.setTotal(db.getAmount() * db.getPrice());
            db.setBill(old); // Liên kết chi tiết với hóa đơn cũ
            totalPrice += db.getTotal();

            // Thêm chi tiết vào danh sách của hóa đơn
            old.getDetail_bills().add(db);
        }

        // Cập nhật tổng tiền hóa đơn
        old.setTotal(totalPrice);

        // Lưu hóa đơn và các chi tiết
        return billRepository.save(old);
    }

    @Override
    public List<Bill> getBillByTableAndTime(int id_table, LocalDate date) {
        return billRepository.findBillsByTableAndDate(id_table, date);
    }

    @Override
    public Detail_Bill delivered(int id, Detail_Bill detail_bill) {
        Detail_Bill db = detailBillRepository.findById(id);
        db.setState(detail_bill.getAmount());
        return detailBillRepository.save(db);
    }

    @Override
    public Bill pay(int bill_id, User user) {
        Bill old = billRepository.findById(bill_id);
        old.setSale_staff(userService.getSaleStaffById(user.getId()));
        old.setState(true);
        return billRepository.save(old);
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> getUnpaids() {
        return billRepository.findByStateFalse();
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
        // Lấy dữ liệu doanh thu và tiền nhập nguyên liệu từ repository
        List<MonthlyRevenueStatistic> revenueStats = billRepository.findMonthlyRevenueStatistics(year);
        List<Object[]> importStats = billRepository.findMonthlyImportStatistics(year);

        // Chuyển đổi importStats thành Map
        Map<Integer, Double> importMap = importStats.stream()
                .collect(Collectors.toMap(
                        row -> (Integer) row[0], // Month
                        row -> (Double) row[1]  // Total Import
                ));

        // Tạo danh sách thống kê cho tất cả 12 tháng
        List<MonthlyRevenueStatistic> fullStats = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            // Tìm doanh thu và tiền nhập nguyên liệu cho tháng hiện tại
            int finalMonth = month;
            double revenue = revenueStats.stream()
                    .filter(stat -> stat.getMonth() == finalMonth)
                    .map(MonthlyRevenueStatistic::getTotalRevenue)
                    .findFirst()
                    .orElse(0.0);

            double totalImport = importMap.getOrDefault(month, 0.0);

            // Thêm thống kê cho tháng hiện tại
            fullStats.add(new MonthlyRevenueStatistic(month, revenue, totalImport));
        }

        return fullStats;
    }

    @Override
    public List<Bill> getBillsByMonth(int month, int year) {
        return billRepository.findBillsByMonth(month, year);
    }

    @Override
    public List<TopDishStatisticOutput> getTopDishesByDateRange(LocalDate startDate, LocalDate endDate) {
        return detailBillRepository.findTopDishesByDateRange(startDate, endDate);
    }
}
