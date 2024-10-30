package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "sale_staff")
public class Sale_Staff extends User{
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale_staff", cascade = CascadeType.ALL)
    private List<Bill>  bills;
}
