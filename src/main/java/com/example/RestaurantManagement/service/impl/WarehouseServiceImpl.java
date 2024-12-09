package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.*;
import com.example.RestaurantManagement.repository.DetailWarehouseRepository;
import com.example.RestaurantManagement.repository.InboundReceiptRepository;
import com.example.RestaurantManagement.repository.OutboundReceiptRepository;
import com.example.RestaurantManagement.repository.WarehouseRepository;
import com.example.RestaurantManagement.service.IngredientService;
import com.example.RestaurantManagement.service.InvoiceService;
import com.example.RestaurantManagement.service.UserService;
import com.example.RestaurantManagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DetailWarehouseRepository detailWarehouseRepository;

    @Override
    public Warehouse getWarehouse() {
        Warehouse warehouse = warehouseRepository.findById(1);

        List<Detail_Warehouse> detail_warehouses = warehouseRepository.calculateCurrentInventory();
        warehouse.setDetail_warehouses(detail_warehouses);

        detailWarehouseRepository.deleteAllDetailWarehouse();
        return warehouse;
    }


}
