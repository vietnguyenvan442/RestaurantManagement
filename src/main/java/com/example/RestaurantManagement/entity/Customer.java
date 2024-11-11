package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User {

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
