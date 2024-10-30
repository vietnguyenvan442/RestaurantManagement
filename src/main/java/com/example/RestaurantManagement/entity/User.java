package com.example.RestaurantManagement.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private String firstname;
    private String lastname;
    private String cccd;
    private String email;
    private String phoneNumber;
    private boolean state;

    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
