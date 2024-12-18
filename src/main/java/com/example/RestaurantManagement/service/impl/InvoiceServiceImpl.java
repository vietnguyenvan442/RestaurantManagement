package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Invoice;
import com.example.RestaurantManagement.entity.Ingredient;
import com.example.RestaurantManagement.entity.Invoice;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.repository.InvoiceRepository;
import com.example.RestaurantManagement.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private UserService userService;

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice add(Invoice invoice) {
        invoice.setSupplier(supplierService.getById(invoice.getSupplier().getId()));
        invoice.setWarehouse_Staff(userService.getWarehouseStaffById(invoice.getWarehouse_Staff().getId()));
        for (Detail_Invoice dv: invoice.getDetail_invoices()){
            double price = dv.getPrice();
            Ingredient ingredient = ingredientService.getById(dv.getIngredient().getId());
            ingredient.setPrice((price));
            if (ingredient != null) dv.setIngredient(ingredient);
            dv.setInvoice(invoice);
        }
        log.info("Success");
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllNotExpired() {
        return invoiceRepository.findAllNotExpired();
    }

    @Override
    public Invoice getById(int id) {
        Invoice invoice = invoiceRepository.findById(id);
        if (invoice == null) throw new ResourceNotFoundException("Invoice not found");
        return invoice;
    }
}
