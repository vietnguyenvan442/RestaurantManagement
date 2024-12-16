package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    private double total;
    private String des;
    private boolean state;

    @JoinColumn(name = "table_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private com.example.RestaurantManagement.entity.Table table;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Detail_Bill> detail_bills;

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
