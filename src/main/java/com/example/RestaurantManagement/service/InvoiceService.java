package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getAll();
    public Invoice add(Invoice invoice);
    public List<Invoice> getAllNotExpired();
    public Invoice getById(int id);
}
