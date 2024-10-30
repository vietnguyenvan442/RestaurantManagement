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
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String des;
    private int state;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Dish> detail_dishes;

    @JsonBackReference
    @JoinColumn(name = "combo_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Combo combo;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Bill> detail_bills;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Detail_Menu> detail_menus;
}
