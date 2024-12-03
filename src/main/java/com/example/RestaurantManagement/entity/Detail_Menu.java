package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

    private int amount;

    @JoinColumn(name = "dish_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Dish dish;

    @JoinColumn(name = "combo_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Combo combo;

    @JsonIgnore
    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Menu menu;
}
