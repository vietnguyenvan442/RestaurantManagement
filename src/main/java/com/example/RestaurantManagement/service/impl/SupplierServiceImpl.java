package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Supplier;
import com.example.RestaurantManagement.exception.AlreadyExistsException;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.SupplierRepository;
import com.example.RestaurantManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getById(int id) {
        Supplier supplier = supplierRepository.findById(id);
        if (supplier == null) throw new ResourceNotFoundException("Supplier not found with id " + id);
        return supplier;
    }

    @Override
    public Supplier add(Supplier supplier) {
        checkNull(supplier);
        checkAlreadyExists(supplier);
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(int id, Supplier supplier) {
        Supplier old = supplierRepository.findById(id);

        checkNull(supplier);
        checkDuplicateName(id, supplier);

        old.setName(supplier.getName());
        old.setAddress(supplier.getAddress());

        return supplierRepository.save(old);
    }

    public void checkNull(Supplier supplier) {
        if (supplier.getName().isEmpty()) throw new ValidationException("Name not null!");
        if (supplier.getAddress().isEmpty()) throw new ValidationException("Address not null!");
        if (supplier.getEmail().isEmpty()) throw new ValidationException("Email not null!");
        if (supplier.getPhoneNumber().isEmpty()) throw new ValidationException("PhoneNumber not null!");
    }

    public void checkAlreadyExists(Supplier supplier) {
        Supplier old = supplierRepository.findByEmail(supplier.getEmail());
        if (old != null) throw new AlreadyExistsException("Supplier Already Exists with Email");
        old = supplierRepository.findByPhoneNumber(supplier.getPhoneNumber());
        if (old != null) throw new AlreadyExistsException("Supplier Already Exists with PhoneNumber");
    }

    public void checkDuplicateName(int id, Supplier supplier) {
        Supplier old = supplierRepository.findById(id);
        if (!old.getEmail().equalsIgnoreCase(supplier.getEmail()) && supplierRepository.findByEmail(supplier.getEmail()) != null)
            throw new AlreadyExistsException("Supplier Already Exists!");
        if (!old.getPhoneNumber().equalsIgnoreCase(supplier.getPhoneNumber()) && supplierRepository.findByEmail(supplier.getPhoneNumber()) != null)
            throw new AlreadyExistsException("Supplier Already Exists!");
    }
}
