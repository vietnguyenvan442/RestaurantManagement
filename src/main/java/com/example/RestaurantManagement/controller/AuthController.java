package com.example.RestaurantManagement.controller;

import com.example.RestaurantManagement.dto.BearerToken;
import com.example.RestaurantManagement.dto.LoginDto;
import com.example.RestaurantManagement.entity.User;
import com.example.RestaurantManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<BearerToken> createAuthenticationToken(@RequestBody LoginDto user) {
        log.info("Received login request for user: {}", user.getUsername());
        BearerToken token = userService.generateToken(user);
        log.info("Generated token");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }
}
