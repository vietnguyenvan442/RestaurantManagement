package com.example.RestaurantManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TopDishStatisticOutput {
    private String dishName;
    private Long totalQuantity;
}
