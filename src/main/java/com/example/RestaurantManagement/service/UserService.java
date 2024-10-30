package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.dto.BearerToken;
import com.example.RestaurantManagement.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public BearerToken generateToken(LoginDto loginDto);
}
