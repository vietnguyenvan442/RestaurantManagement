package com.example.RestaurantManagement.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String des;
    private double value;
    private Date start_date;
    private Date end_date;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voucher", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
