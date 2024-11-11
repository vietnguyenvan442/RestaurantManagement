package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.BearerToken;
import com.example.RestaurantManagement.dto.LoginDto;
import com.example.RestaurantManagement.entity.User;

import java.util.List;

public interface UserService {
    public BearerToken generateToken(LoginDto loginDto);

    public List<User> getAll();

    public User getById(int id);

    public User add(User user);

    public User update(int id, User user);

    public void delete(int id);

    public void saveManager();

    public void saveWarehouseStaff();

    public void saveSaleStaff();
}
