package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Invoice;
import com.example.RestaurantManagement.entity.Ingredient;
import com.example.RestaurantManagement.entity.Invoice;
import com.example.RestaurantManagement.repository.InvoiceRepository;
import com.example.RestaurantManagement.repository.WarehouseRepository;
import com.example.RestaurantManagement.service.IngredientService;
import com.example.RestaurantManagement.service.InvoiceService;
import com.example.RestaurantManagement.service.SupplierService;
import com.example.RestaurantManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Invoice add(Invoice invoice) {
        invoice.setSupplier(supplierService.getById(invoice.getSupplier().getId()));
        invoice.setWarehouse_Staff(warehouseRepository.findById(invoice.getWarehouse_Staff().getId()));
        for (Detail_Invoice dv: invoice.getDetail_invoices()){
            Ingredient ingredient = ingredientService.getById(dv.getIngredient().getId());
            if (ingredient != null) dv.setIngredient(ingredient);
            dv.setInvoice(invoice);
        }
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllNotExpired() {
        return invoiceRepository.findAllNotExpired();
    }
}
