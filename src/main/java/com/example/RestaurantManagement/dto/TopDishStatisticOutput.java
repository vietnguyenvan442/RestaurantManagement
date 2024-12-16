package com.example.RestaurantManagement.dto;

import com.example.RestaurantManagement.entity.Dish;
import com.example.RestaurantManagement.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TopDishStatisticOutput {
    private Dish dish;
    private Long totalQuantity;
}
