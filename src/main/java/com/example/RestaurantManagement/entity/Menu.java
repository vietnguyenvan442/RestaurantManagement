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
@Table(name = "menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private String name;
    private String des;
    private boolean state;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Detail_Menu> detail_menuList;
}
