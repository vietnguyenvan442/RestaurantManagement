package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Ingredient;
import com.example.RestaurantManagement.exception.AlreadyExistsException;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.IngredientRepository;
import com.example.RestaurantManagement.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getAll(){
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getById(int id) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null) throw new ResourceNotFoundException("Ingredient not found with id " + id);
        return ingredient;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (checkNull(ingredient)) throw new ValidationException("Name and Price not null!");
        if (checkAlreadyExists(ingredient)) throw new AlreadyExistsException("Ingredient Already Exists!");
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(int id, Ingredient ingredient) {
        Ingredient old = ingredientRepository.findById(id);

        if (checkNull(ingredient)) throw new ValidationException("Name and Price not null!");
        if (checkDuplicateName(id, ingredient)) throw new AlreadyExistsException("Duplicate Names");

        old.setName(ingredient.getName());
        old.setDes(ingredient.getDes());
        old.setPrice(ingredient.getPrice());

        return ingredientRepository.save(old);
    }

    public boolean checkNull(Ingredient ingredient){
        if (ingredient.getName().isEmpty()) return true;
        if (ingredient.getPrice() == null) return true;
        return false;
    }

    public boolean checkAlreadyExists(Ingredient ingredient){
        Ingredient old = ingredientRepository.findByName(ingredient.getName());
        if (old != null) return true;
        return false;
    }

    public boolean checkDuplicateName(int id, Ingredient ingredient){
        Ingredient old = ingredientRepository.findById(id);
        if (!old.getName().equalsIgnoreCase(ingredient.getName()) && ingredientRepository.findByName(ingredient.getName()) != null) return true;
        return false;
    }
}