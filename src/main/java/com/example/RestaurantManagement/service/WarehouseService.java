package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.entity.Outbound_Receipt;
import com.example.RestaurantManagement.entity.Warehouse;

public interface WarehouseService {
    public Inbound_Receipt inboundWarehouse(Inbound_Receipt inbound_receipt);
    public Outbound_Receipt outboundWarehouse(Outbound_Receipt outbound_receipt);
    public Warehouse getWarehouse();
}
