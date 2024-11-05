package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "detail_outbound")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Outbound implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private double price;
    private double total;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @JsonBackReference
    @JoinColumn(name = "outbound_receipt_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Outbound_Receipt outbound_receipt;
}
