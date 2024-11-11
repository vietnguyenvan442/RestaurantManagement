package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sale_staff")
public class Sale_Staff extends User {
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale_staff", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
