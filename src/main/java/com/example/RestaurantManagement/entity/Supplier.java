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
@Table(name = "supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
}