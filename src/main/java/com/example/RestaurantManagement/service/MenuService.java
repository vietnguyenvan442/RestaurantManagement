package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getAll();

    public Menu getById(int id);

    public Menu getByActive();

    public Menu add(Menu menu);

    public Menu update(int id, Menu menu);

    public void active(int id);
}
