package com.example.RestaurantManagement.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "inbound_receipt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inbound_Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @JoinColumn(name = "invoice_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Invoice invoice;

    @JoinColumn(name = "warehouse_staff_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse_Staff warehouse_staff;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "inbound_receipt", cascade = CascadeType.ALL)
    private List<Detail_Inbound> detail_inbounds;

    private double total;
    public double getTotal(){
        double sum = 0;
        for (Detail_Inbound di: detail_inbounds){
            sum += di.getTotal();
        }
        return sum;
    }
}
