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
@Table(name = "detail_combo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Combo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @JoinColumn(name = "dish_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Dish dish;

    @JsonIgnore
    @JoinColumn(name = "combo_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Combo combo;
}
