package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;

@Entity
@Table(name = "detail_warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Warehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;
    private String unit;
    private double price;
    private double total;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @JsonIgnore
    @JoinColumn(name = "warehouse_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;

    public Detail_Warehouse(double amount, String unit, double price, double total, Ingredient ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.price = price;
        this.total = total;
        this.ingredient = ingredient;
    }
}
