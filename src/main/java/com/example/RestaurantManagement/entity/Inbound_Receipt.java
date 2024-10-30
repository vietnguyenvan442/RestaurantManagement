package com.example.RestaurantManagement.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "inbound_receipt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inbound_Receipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @JoinColumn(name = "warehouse_staff_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse_Staff warehouse_staff;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inbound_receipt", cascade = CascadeType.ALL)
    private List<Detail_Inbound> detail_inboundList;
}
