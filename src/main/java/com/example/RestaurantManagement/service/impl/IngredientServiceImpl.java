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
    public List<Ingredient> getAll() {
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
        if (checkNull(ingredient)) {
            log.info("Error Validate!");
            throw new ValidationException("Error Validate!");
        }
        if (checkAlreadyExists(ingredient)) {
            log.info("Ingredient Already Exists!");
            throw new AlreadyExistsException("Ingredient Already Exists!");
        }
        log.info("Successfull");
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(int id, Ingredient ingredient) {
        Ingredient old = ingredientRepository.findById(id);

        if (checkNull(ingredient)) throw new ValidationException("Error Validate!");
        if (checkDuplicateName(id, ingredient)) throw new AlreadyExistsException("Duplicate Names");

        old.setName(ingredient.getName());
        old.setDes(ingredient.getDes());
        old.setPrice(ingredient.getPrice());
        old.setImage(ingredient.getImage());
        old.setUnit(ingredient.getUnit());

        return ingredientRepository.save(old);
    }

    public boolean checkNull(Ingredient ingredient) {
        if (ingredient.getName().isEmpty()) return true;
        if (ingredient.getPrice() == 0) return true;
        if (ingredient.getImage() == null) return true;
        if (ingredient.getUnit() == null) return true;
        return false;
    }

    public boolean checkAlreadyExists(Ingredient ingredient) {
        Ingredient old = ingredientRepository.findByName(ingredient.getName());
        if (old != null) return true;
        return false;
    }

    public boolean checkDuplicateName(int id, Ingredient ingredient) {
        Ingredient old = ingredientRepository.findById(id);
        if (!old.getName().equalsIgnoreCase(ingredient.getName()) && ingredientRepository.findByName(ingredient.getName()) != null)
            return true;
        return false;
    }
}
