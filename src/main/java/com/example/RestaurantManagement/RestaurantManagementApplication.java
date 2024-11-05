package com.example.RestaurantManagement;

import com.example.RestaurantManagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args ->{
//			userService.saveManager();
//			userService.saveWarehouseStaff();
//			userService.saveSaleStaff();
		};
	}
}
