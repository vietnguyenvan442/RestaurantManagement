package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.BearerToken;
import com.example.RestaurantManagement.dto.LoginDto;
import com.example.RestaurantManagement.entity.Sale_Staff;
import com.example.RestaurantManagement.entity.User;
import com.example.RestaurantManagement.entity.Warehouse_Staff;

import java.util.List;

public interface UserService {
    public BearerToken generateToken(LoginDto loginDto);

    public List<User> getAll();

    public User getById(int id);

    public User add(User user);

    public User update(int id, User user);

    public void delete(int id);

    public Warehouse_Staff getWarehouseStaffById(int id);

    public Sale_Staff getSaleStaffById(int id);
}
