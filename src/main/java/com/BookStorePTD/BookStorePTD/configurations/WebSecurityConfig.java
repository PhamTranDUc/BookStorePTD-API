package com.BookStorePTD.BookStorePTD.configurations;

import com.BookStorePTD.BookStorePTD.filters.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer:: disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        requests ->{
                            requests.requestMatchers(
                                    "/api/v1/users/login",
                                    "/api/v1/users/register"
                                            ).permitAll()
                                    .requestMatchers(HttpMethod.GET,"/api/v1/products",
                                            "/api/v1/categories").permitAll()
                                    .requestMatchers(HttpMethod.POST,"/api/v1/products").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,"/api/v1/products/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasRole("ADMIN")
                                    .anyRequest().authenticated();
                        }
                );
        return http.build();
    }
}
