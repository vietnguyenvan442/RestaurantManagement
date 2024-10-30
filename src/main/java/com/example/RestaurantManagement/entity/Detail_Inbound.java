package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @JsonBackReference
    @JoinColumn(name = "inbound_receipt_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Inbound_Receipt inbound_receipt;
}
