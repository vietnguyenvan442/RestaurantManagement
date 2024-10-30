package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "warehouse_staff")
public class Warehouse_Staff extends User{
    @JsonBackReference(value = "warehouse_staff_invoices")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_Staff", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @JsonBackReference(value = "warehouse_staff_inbound_receipt")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_staff", cascade = CascadeType.ALL)
    private List<Inbound_Receipt> inbound_receipts;

    @JsonBackReference(value = "warehouse_staff_outbound_receipt")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse_staff", cascade = CascadeType.ALL)
    private List<Outbound_Receipt> outbound_receipts;
}
