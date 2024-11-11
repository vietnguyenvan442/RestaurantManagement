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
@Getter
@Setter
@Table(name = "combo")
@AllArgsConstructor
@NoArgsConstructor
public class Combo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Detail_Bill> detailBills;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Detail_Menu> detailMenus;
}
