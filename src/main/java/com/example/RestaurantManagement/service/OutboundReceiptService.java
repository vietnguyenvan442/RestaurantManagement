package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.entity.Outbound_Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OutboundReceiptService {
    public List<Outbound_Receipt> getAll();
    public Outbound_Receipt add(Outbound_Receipt outbound_receipt);
}
