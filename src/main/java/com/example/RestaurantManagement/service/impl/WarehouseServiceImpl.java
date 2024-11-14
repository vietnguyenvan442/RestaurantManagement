package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.*;
import com.example.RestaurantManagement.repository.InboundReceiptRepository;
import com.example.RestaurantManagement.repository.OutboundReceiptRepository;
import com.example.RestaurantManagement.repository.WarehouseRepository;
import com.example.RestaurantManagement.service.IngredientService;
import com.example.RestaurantManagement.service.InvoiceService;
import com.example.RestaurantManagement.service.UserService;
import com.example.RestaurantManagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private InboundReceiptRepository inboundReceiptRepository;

    @Autowired
    private OutboundReceiptRepository outboundReceiptRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public Inbound_Receipt inboundWarehouse(Inbound_Receipt inbound_receipt) {
        inbound_receipt.setWarehouse_staff(userService.getWarehouseStaffById(inbound_receipt.getWarehouse_staff().getId()));
        inbound_receipt.setInvoice(invoiceService.getById(inbound_receipt.getInvoice().getId()));
        for (Detail_Inbound di: inbound_receipt.getDetail_inbounds()){
            di.setIngredient(ingredientService.getById(di.getIngredient().getId()));
            di.setInbound_receipt(inbound_receipt);
        }
        return inboundReceiptRepository.save(inbound_receipt);
    }

    @Override
    public Outbound_Receipt outboundWarehouse(Outbound_Receipt outbound_receipt) {
        outbound_receipt.setWarehouse_staff(userService.getWarehouseStaffById(outbound_receipt.getWarehouse_staff().getId()));
        for (Detail_Outbound d: outbound_receipt.getDetail_outbounds()){
            d.setIngredient(ingredientService.getById(d.getIngredient().getId()));
            d.setOutbound_receipt(outbound_receipt);
        }
        return outboundReceiptRepository.save(outbound_receipt);
    }

    @Override
    public Warehouse getWarehouse() {
        Warehouse warehouse = warehouseRepository.findById(1);

        List<Detail_Warehouse> detail_warehouses = warehouseRepository.calculateCurrentInventory();
        warehouse.setDetail_warehouses(detail_warehouses);

        return warehouseRepository.save(warehouse);
    }


}
