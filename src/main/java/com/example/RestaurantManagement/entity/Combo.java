package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @JsonBackReference(value = "combo-detailBill")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Detail_Bill> detailBills;

    @JsonBackReference(value = "combo-detailMenu")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo", cascade = CascadeType.ALL)
    private List<Detail_Menu> detailMenus;
}
