package com.example.RestaurantManagement.service.impl;

import com.example.RestaurantManagement.entity.Detail_Menu;
import com.example.RestaurantManagement.entity.Menu;
import com.example.RestaurantManagement.exception.AlreadyExistsException;
import com.example.RestaurantManagement.exception.ResourceNotFoundException;
import com.example.RestaurantManagement.exception.ValidationException;
import com.example.RestaurantManagement.repository.MenuRepository;
import com.example.RestaurantManagement.service.ComboSerivce;
import com.example.RestaurantManagement.service.DishService;
import com.example.RestaurantManagement.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishService dishService;

    @Autowired
    private ComboSerivce comboSerivce;

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(int id) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) throw new ResourceNotFoundException("Menu not found with id: " + id);
        return menu;
    }

    @Override
    public Menu add(Menu menu) {
        checkNull(menu);
        checkAlreadyExists(menu);

        for (Detail_Menu dm : menu.getDetail_menuList()) {
            if (dm.getDish() != null) {
                dm.setDish(dishService.getById(dm.getDish().getId()));
                dm.setMenu(menu);
            }
            if (dm.getCombo() != null) {
                dm.setCombo(comboSerivce.getById(dm.getCombo().getId()));
                dm.setMenu(menu);
            }
        }
        menu.setDate(Date.valueOf(LocalDate.now()));
        menu.setState(false);

        return menuRepository.save(menu);
    }

    @Override
    public Menu update(int id, Menu menu) {
        checkNull(menu);
        Menu old = menuRepository.findById(id);
        if (!old.getName().equals(menu.getName())) checkAlreadyExists(menu);

        for (Detail_Menu dm : menu.getDetail_menuList()) {
            if (dm.getDish() != null) {
                dm.setDish(dishService.getById(dm.getDish().getId()));
                dm.setMenu(menu);
            }
            if (dm.getCombo() != null) {
                dm.setCombo(comboSerivce.getById(dm.getCombo().getId()));
                dm.setMenu(menu);
            }
        }

        old.setDetail_menuList(menu.getDetail_menuList());
        old.setName(menu.getName());
        old.setDate(Date.valueOf(LocalDate.now()));
        old.setDes(menu.getDes());

        return menuRepository.save(old);
    }

    @Override
    public void active(int id) {
        List<Menu> menus = getAll();
        for (Menu m : menus) {
            m.setState(false);
        }
        menuRepository.saveAll(menus);

        Menu menu = menuRepository.findById(id);
        menu.setState(true);
        menuRepository.save(menu);
    }

    public void checkNull(Menu menu) {
        if (menu.getName().isEmpty()) throw new ValidationException("Name's menu not null");
        if (menu.getDetail_menuList().isEmpty()) throw new ValidationException("List dish or combo menu not null");
    }

    public void checkAlreadyExists(Menu menu) {
        Menu old = menuRepository.findByName(menu.getName());
        if (old != null) throw new AlreadyExistsException("Name's menu already exists");
    }
}
