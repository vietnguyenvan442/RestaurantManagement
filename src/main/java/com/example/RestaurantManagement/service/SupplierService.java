package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Supplier;

import java.util.List;

public interface SupplierService {
    public List<Supplier> getAll();

    public Supplier getById(int id);

    public Supplier add(Supplier supplier);

    public Supplier update(int id, Supplier supplier);
}
