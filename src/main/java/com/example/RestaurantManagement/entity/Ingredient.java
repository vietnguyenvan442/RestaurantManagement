package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String name;
    private String des;
    private Double price;
    private String unit;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Inbound> detail_inbounds;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Outbound> detail_outbounds;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Invoice> detail_invoices;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Dish> detail_dishes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Warehouse> detail_warehouses;
}
