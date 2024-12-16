package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Dish;
import com.example.RestaurantManagement.entity.Dish;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.DishRepository;
import com.example.RestaurantManagement.service.DishService;
import com.example.RestaurantManagement.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getById(int id) {
        Dish dish = dishRepository.findById(id);
        if (dish == null) throw new ResourceNotFoundException("Dish not found with id :" + id);
        return dish;
    }

    @Override
    public Dish add(Dish dish) {
        if (dish.getDetail_dishes().isEmpty()) throw new ValidationException("Detail dish not null");
        for (Detail_Dish d : dish.getDetail_dishes()) {
            d.setIngredient(ingredientService.getById(d.getIngredient().getId()));
            d.setDish(dish);
        }
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(int id, Dish dish) {
        Dish new_dish = dishRepository.findById(id);
        new_dish.setName(dish.getName());
        new_dish.setDes(dish.getDes());
        new_dish.setImage(dish.getImage());
        new_dish.setPrice(dish.getPrice());

        for (Detail_Dish d : dish.getDetail_dishes()) {
            d.setIngredient(ingredientService.getById(d.getIngredient().getId()));
            d.setDish(new_dish);
        }
        new_dish.setDetail_dishes(dish.getDetail_dishes());

        return dishRepository.save(new_dish);
    }
}
