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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

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
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/users/info").permitAll()
                        .requestMatchers("/bills/**").hasRole("SALE")
                        .requestMatchers("/bills/book-table").hasAnyRole("SALE", "CUSTOMER")
                        .requestMatchers("/bills/book-dish").hasAnyRole("SALE", "CUSTOMER")
                        .requestMatchers("/bills/customer/{id_cus}").hasRole("CUSTOMER")
                        .requestMatchers("/ingredients/**").hasRole("WAREHOUSE")
                        .requestMatchers("/suppliers/**").hasRole("WAREHOUSE")
                        .requestMatchers("/invoices/**").hasRole("WAREHOUSE")
                        .requestMatchers("/warehouses/**").hasRole("WAREHOUSE")
                        .requestMatchers("/dishs/**").hasRole("MANAGER")
                        .requestMatchers("/combos/**").hasRole("MANAGER")
                        .requestMatchers("/menus/**").hasRole("MANAGER")
                        .requestMatchers("/users/**").hasRole("MANAGER")
                        .requestMatchers("/statistic/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
