package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "warehouse_staff")
public class Warehouse_Staff extends User{
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_Staff", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_staff", cascade = CascadeType.ALL)
    private List<Inbound_Receipt> inbound_receipts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_staff", cascade = CascadeType.ALL)
    private List<Outbound_Receipt> outbound_receipts;
}
