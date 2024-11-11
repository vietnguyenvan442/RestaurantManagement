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

@Entity
@Table(name = "outbound_receipt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Outbound_Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @JoinColumn(name = "warehouse_staff_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse_Staff warehouse_staff;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "outbound_receipt", cascade = CascadeType.ALL)
    private List<Detail_Outbound> detail_outbounds;
}
