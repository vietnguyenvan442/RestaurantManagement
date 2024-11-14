package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Customer;
import com.example.RestaurantManagement.repository.CustomerRepository;
import com.example.RestaurantManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CutomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id);
    }
}
