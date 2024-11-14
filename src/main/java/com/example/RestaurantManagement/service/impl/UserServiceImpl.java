package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.dto.BearerToken;
import com.example.RestaurantManagement.dto.LoginDto;
import com.example.RestaurantManagement.entity.*;
import com.example.RestaurantManagement.exception.AlreadyExistsException;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.*;
import com.example.RestaurantManagement.service.RoleService;
import com.example.RestaurantManagement.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements com.example.RestaurantManagement.service.UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseStaffRepository warehouseStaffRepository;

    @Autowired
    private SaleStaffRepository saleStaffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public BearerToken generateToken(LoginDto loginDto) {
        log.info("Generating token for login dto: {}", loginDto);

        if (!checkState(loginDto.getUsername())) {
            log.error("User not found or inactive: {}", loginDto.getUsername());
            throw new ValidationException("Incorrect username or password");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", loginDto.getUsername(), e);
            throw new ValidationException("Incorrect username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        log.info("Generated Access Token: {}", accessToken);
        log.info("Generated Refresh Token: {}", refreshToken);

        return new BearerToken(accessToken, refreshToken);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        User user = userRepository.findById(id);
        if (user == null) throw new ResourceNotFoundException("User not found with id " + id);
        return user;
    }

    @Override
    public User add(User user) {
        checkNull(user);
        checkAlreadyExists(user);

        user.setRole(roleService.findById(user.getRole().getId()));

        if (user.getRole().getId() == 2) {
            Warehouse_Staff warehouseStaff = new Warehouse_Staff();
            warehouseStaff.setUsername(user.getUsername());
            warehouseStaff.setPassword(passwordEncoder.encode(user.getPassword()));
            warehouseStaff.setName(user.getName());
            warehouseStaff.setCccd(user.getCccd());
            warehouseStaff.setEmail(user.getEmail());
            warehouseStaff.setPhoneNumber(user.getPhoneNumber());
            warehouseStaff.setDob(user.getDob());
            warehouseStaff.setAddress(user.getAddress());
            warehouseStaff.setRole(user.getRole());
            warehouseStaff.setState(true);
            return warehouseStaffRepository.save(warehouseStaff);
        } else if (user.getRole().getId() == 3) {
            Sale_Staff saleStaff = new Sale_Staff();
            saleStaff.setUsername(user.getUsername());
            saleStaff.setPassword(passwordEncoder.encode(user.getPassword()));
            saleStaff.setCccd(user.getCccd());
            saleStaff.setEmail(user.getEmail());
            saleStaff.setPhoneNumber(user.getPhoneNumber());
            saleStaff.setDob(user.getDob());
            saleStaff.setAddress(user.getAddress());
            saleStaff.setRole(user.getRole());
            saleStaff.setState(true);
            return saleStaffRepository.save(saleStaff);
        } else {
            Customer customer = new Customer();
            customer.setUsername(user.getUsername());
            customer.setPassword(passwordEncoder.encode(user.getPassword()));
            customer.setCccd(user.getCccd());
            customer.setEmail(user.getEmail());
            customer.setPhoneNumber(user.getPhoneNumber());
            customer.setDob(user.getDob());
            customer.setAddress(user.getAddress());
            customer.setRole(user.getRole());
            customer.setState(true);
            return customerRepository.save(customer);
        }
    }


    public void checkAlreadyExists(User user) {
        User old = userRepository.findByCccd(user.getCccd());
        if (old != null) throw new AlreadyExistsException("Cccd Already Exists!");
        old = userRepository.findByEmail(user.getEmail());
        if (old != null) throw new AlreadyExistsException("Email Already Exists!");
        old = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (old != null) throw new AlreadyExistsException("PhoneNumber Already Exists!");
    }

    @Override
    public User update(int id, User user) {
        checkNull(user);
        User old = userRepository.findById(id);
        old.setName(user.getName());
        old.setDob(user.getDob());
        old.setAddress(user.getAddress());
        return userRepository.save(old);
    }

    public void checkNull(User user) {
        if (user.getUsername().isEmpty()) throw new ValidationException("Username not null");
        if (user.getPassword().isEmpty()) throw new ValidationException("Password not null");
        if (user.getName().isEmpty()) throw new ValidationException("Name not null");
        if (user.getAddress().isEmpty()) throw new ValidationException("Address not null");
        if (user.getCccd().isEmpty()) throw new ValidationException("Cccd not null");
        if (user.getEmail().isEmpty()) throw new ValidationException("Email not null");
        if (user.getPhoneNumber().isEmpty()) throw new ValidationException("PhoneNumber not null");
        if (user.getRole() == null || user.getRole().getId() == 0) throw new ValidationException("Role not null");
        if (user.getDob() == null) throw new ValidationException("Date of birth not null");
    }

    @Override
    public void delete(int id) {
        User user = userRepository.findById(id);
        user.setState(false);
        userRepository.save(user);
    }

    public boolean checkState(String username) {
        log.info("Checking state for username: {}", username);
        return getUserByUsername(username).map(User::isState).orElse(false);
    }

    public Optional<User> getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public Warehouse_Staff getWarehouseStaffById(int id) {
        Warehouse_Staff warehouse_staff = warehouseStaffRepository.findById(id);
        if (warehouse_staff == null) throw new ResourceNotFoundException("Warehouse_Staff not found with id: " + id);
        return warehouse_staff;
    }

    @Override
    public Sale_Staff getSaleStaffById(int id) {
        Sale_Staff sale_staff = saleStaffRepository.findById(id);
        if (sale_staff == null) throw new ResourceNotFoundException("Sale_Staff not found with id: " + id);
        return sale_staff;
    }
}
