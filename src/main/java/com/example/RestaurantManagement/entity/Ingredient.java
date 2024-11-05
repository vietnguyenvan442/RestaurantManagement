package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @JsonBackReference(value = "ingredient_detail_inbound")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Inbound> detail_inbounds;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Outbound> detail_outbounds;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Dish> detail_dishes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<Detail_Warehouse> detail_warehouses;
}
