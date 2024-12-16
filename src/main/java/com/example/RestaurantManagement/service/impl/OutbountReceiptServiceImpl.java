package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Inbound;
import com.example.RestaurantManagement.entity.Detail_Outbound;
import com.example.RestaurantManagement.entity.Inbound_Receipt;
import com.example.RestaurantManagement.entity.Outbound_Receipt;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.InboundReceiptRepository;
import com.example.RestaurantManagement.repository.OutboundReceiptRepository;
import com.example.RestaurantManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutbountReceiptServiceImpl implements OutboundReceiptService {
    @Autowired
    private OutboundReceiptRepository outboundReceiptRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Outbound_Receipt> getAll() {
        return outboundReceiptRepository.findAll();
    }

    @Override
    public Outbound_Receipt add(Outbound_Receipt outbound_receipt) {
        outbound_receipt.setWarehouse_staff(userService.getWarehouseStaffById(outbound_receipt.getWarehouse_staff().getId()));

        if (outbound_receipt.getDetail_outbounds().isEmpty()) throw new ValidationException("List detail outbound not null");

        for (Detail_Outbound di: outbound_receipt.getDetail_outbounds()){
            di.setIngredient(ingredientService.getById(di.getIngredient().getId()));
            di.setOutbound_receipt(outbound_receipt);
        }
        return outboundReceiptRepository.save(outbound_receipt);
    }
}
