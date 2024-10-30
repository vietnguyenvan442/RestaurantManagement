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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detail_menu")
public class Detail_Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "dish_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dish dish;

    @JoinColumn(name = "combo_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Combo combo;

    @JsonBackReference
    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
}
