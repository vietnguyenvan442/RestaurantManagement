package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse_Staff warehouse_Staff;

    @JoinColumn(name = "supplier_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Detail_Invoice> detail_invoices;

    @JsonIgnore
    @JoinColumn(name = "inbound_receipt_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Inbound_Receipt inbound_receipt;
}
