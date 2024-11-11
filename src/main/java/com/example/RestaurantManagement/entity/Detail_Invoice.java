package com.example.RestaurantManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "detail_invoice")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Detail_Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @JsonIgnore
    @JoinColumn(name = "invoice_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Invoice invoice;
}
