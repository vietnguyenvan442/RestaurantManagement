package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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

    private String name;
    private String des;
    private Double price;

    @JsonBackReference(value = "ingredient_detail_inbound")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Inbound> detail_menus;

    @JsonBackReference(value = "ingredient_detail_outbound")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Outbound> detail_outbounds;

    @JsonBackReference(value = "ingredient_invoice")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @JsonBackReference(value = "ingredient_detail_dish")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Dish> detail_dishes;

    @JsonBackReference(value = "ingredient_detail_warehouse")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Warehouse> detail_warehouses;
}
