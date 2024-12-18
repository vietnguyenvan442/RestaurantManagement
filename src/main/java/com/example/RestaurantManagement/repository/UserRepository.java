package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);

    User findByCccd(String cccd);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);
}
