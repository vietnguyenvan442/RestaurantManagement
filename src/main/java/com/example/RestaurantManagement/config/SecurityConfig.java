package com.example.RestaurantManagement.config;

import com.example.RestaurantManagement.security.UserDetailsServiceImpl;
import com.example.RestaurantManagement.util.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/users/info").permitAll()
                        .requestMatchers("/tables/available", "/bills/book-table", "/bills/book-dish").hasAnyRole("SALE", "CUSTOMER")
                        .requestMatchers("/bills/customer/{id_cus}").hasRole("CUSTOMER")
                        .requestMatchers("/bills/search/{id_table}/{date}").hasAnyRole("CUSTOMER", "SALE")
                        .requestMatchers("/bills/**").hasRole("SALE")
                        .requestMatchers("/upload/**", "/ingredients/**").hasAnyRole("WAREHOUSE", "MANAGER")
                        .requestMatchers("/suppliers/**", "/invoices/**", "/warehouses/**", "/inbound-receipts/**", "/outbound-receipts/**").hasRole("WAREHOUSE")
                        .requestMatchers("/dishes/**", "/combos/**").hasRole("MANAGER")
                        .requestMatchers("/menus/active").permitAll()
                        .requestMatchers("/menus/**", "/users/**", "/statistic/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
