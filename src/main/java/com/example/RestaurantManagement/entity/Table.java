package com.example.RestaurantManagement.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "'table'")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String des;
    private String address;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
