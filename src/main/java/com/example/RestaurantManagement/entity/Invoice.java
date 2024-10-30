package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date start_date;
    private Date end_date;

    @JoinColumn(name = "warehouse_staff_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse_Staff warehouse_Staff;

    @JoinColumn(name = "supplier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @JsonBackReference(value = "invoice_ingredient")
    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @JsonBackReference(value = "invoice_outbound_receipt")
    @JoinColumn(name = "outbound_receipt_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Outbound_Receipt outbound_receipt;
}
