package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Combo;
import com.example.RestaurantManagement.entity.Dish;
import com.example.RestaurantManagement.exception.AlreadyExistsException;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.ComboRepository;
import com.example.RestaurantManagement.service.ComboSerivce;
import com.example.RestaurantManagement.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ComboServiceImpl implements ComboSerivce {
    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private DishService dishService;

    @Override
    public List<Combo> getAll() {
        return comboRepository.findAll();
    }

    @Override
    public Combo getById(int id) {
        Combo combo = comboRepository.findById(id);
        if (combo == null) throw new ResourceNotFoundException("Combo not found with id: " + id);
        return combo;
    }

    @Override
    public Combo add(Combo combo) {
        checkNull(combo);
        checkAlreadyExists(combo);

        List<Dish> managedDishes = new ArrayList<>();
        for (Dish d : combo.getDishes()) {
            Dish managedDish = dishService.getById(d.getId());
            managedDish.setCombo(combo);
            managedDishes.add(managedDish);
        }
        combo.setDishes(managedDishes);

        return comboRepository.save(combo);
    }

    @Override
    public Combo update(int id, Combo combo) {
        checkNull(combo);
        Combo old = comboRepository.findById(id);
        if (!Objects.equals(old.getName(), combo.getName())) checkAlreadyExists(combo);

        old.setName(combo.getName());
        old.setDes(combo.getDes());
        old.setImage(combo.getImage());
        List<Dish> managedDishes = new ArrayList<>();
        for (Dish d : combo.getDishes()) {
            Dish managedDish = dishService.getById(d.getId());
            managedDish.setCombo(combo);
            managedDishes.add(managedDish);
        }
        old.setDishes(managedDishes);

        return comboRepository.save(old);
    }

    public void checkNull(Combo combo) {
        if (combo.getDishes().isEmpty()) throw new ValidationException("List Dish not null");
        if (combo.getName().isEmpty()) throw new ValidationException("Name combo not null");
    }

    public void checkAlreadyExists(Combo combo) {
        Combo old = comboRepository.findByName(combo.getName());
        if (old != null) throw new AlreadyExistsException("Combo Already Exists");
    }
}
