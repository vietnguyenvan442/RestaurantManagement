package com.example.RestaurantManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends User{
}
