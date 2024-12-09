package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InboundReceiptService {
    public List<Inbound_Receipt> getAll();
    public Inbound_Receipt add(Inbound_Receipt inbound_receipt);
}
