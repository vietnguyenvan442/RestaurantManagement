package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Inbound;
import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.InboundReceiptRepository;
import com.example.RestaurantManagement.service.InboundReceiptService;
import com.example.RestaurantManagement.service.IngredientService;
import com.example.RestaurantManagement.service.InvoiceService;
import com.example.RestaurantManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InbountReceiptServiceImpl implements InboundReceiptService {
    @Autowired
    private InboundReceiptRepository inboundReceiptRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Inbound_Receipt> getAll() {
        return inboundReceiptRepository.findAll();
    }

    @Override
    public Inbound_Receipt add(Inbound_Receipt inbound_receipt) {
        inbound_receipt.setWarehouse_staff(userService.getWarehouseStaffById(inbound_receipt.getWarehouse_staff().getId()));
        inbound_receipt.setInvoice(invoiceService.getById(inbound_receipt.getInvoice().getId()));

        if (inbound_receipt.getDetail_inbounds().isEmpty()) throw new ValidationException("List detail inbound not null");

        for (Detail_Inbound di: inbound_receipt.getDetail_inbounds()){
            di.setIngredient(ingredientService.getById(di.getIngredient().getId()));
            di.setInbound_receipt(inbound_receipt);
        }
        return inboundReceiptRepository.save(inbound_receipt);
    }

    @Override
    public List<Inbound_Receipt> getStatByMonth(int month, int year) {
        return inboundReceiptRepository.findBillsByMonth(month, year);
    }
}
