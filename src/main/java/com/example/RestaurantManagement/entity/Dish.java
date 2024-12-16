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
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String name;
    private String des;
    private double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Dish> detail_dishes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Combo> detail_combos;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Bill> detail_bills;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Menu> detail_menus;
}
