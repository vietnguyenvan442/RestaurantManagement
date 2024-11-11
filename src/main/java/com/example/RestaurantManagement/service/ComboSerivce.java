package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Combo;

import java.util.List;

public interface ComboSerivce {
    public List<Combo> getAll();

    public Combo getById(int id);

    public Combo add(Combo combo);

    public Combo update(int id, Combo combo);
}
