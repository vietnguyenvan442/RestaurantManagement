package com.example.RestaurantManagement.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Detail_Warehouse> detail_warehouseList;
}
