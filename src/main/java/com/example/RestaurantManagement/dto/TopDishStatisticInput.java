package com.example.RestaurantManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TopDishStatisticInput {
    private LocalDate start_date;
    private LocalDate end_date;
}
