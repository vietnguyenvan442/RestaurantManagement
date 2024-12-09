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
@Table(name = "detail_inbound")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Inbound implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private double price;
    private double total;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @JsonIgnore
    @JoinColumn(name = "inbound_receipt_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Inbound_Receipt inbound_receipt;
}
