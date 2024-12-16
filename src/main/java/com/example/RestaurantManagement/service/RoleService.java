package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Role;

import java.util.List;

public interface RoleService {
    public Role findById(int id);
    public List<Role> findAll();
}
