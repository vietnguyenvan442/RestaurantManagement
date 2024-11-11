package com.example.RestaurantManagement.entity;

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
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private double total;
    private double price;

    @JoinColumn(name = "table_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private com.example.RestaurantManagement.entity.Table table;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Detail_Bill> detailBills;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @JoinColumn(name = "sale_staff_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sale_Staff sale_staff;

    @JoinColumn(name = "voucher_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Voucher voucher;

}
