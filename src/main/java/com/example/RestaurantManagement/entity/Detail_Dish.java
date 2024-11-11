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
@Table(name = "detail_dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String amount;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @JsonIgnore
    @JoinColumn(name = "dish_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Dish dish;
}
