package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Role;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.repository.RoleRepository;
import com.example.RestaurantManagement.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        log.info("Fetching role by id: {}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Role not found with id: {}", id);
                    return new ResourceNotFoundException("Role not found with id " + id);
                });
    }
}
