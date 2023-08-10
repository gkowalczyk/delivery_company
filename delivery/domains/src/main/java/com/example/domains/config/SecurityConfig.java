package com.example.domains.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/jms/{customerId}/downloadFile/{fileName:.+}")
               // .hasAnyRole("user")
                .permitAll()
                .requestMatchers("/v1jms/{customerId}/downloadFile/{fileName:.+}")
                // .hasAnyRole("user")
                .permitAll()
                .requestMatchers("/v1/jms/api/order/{customerId}")
                .hasAnyRole("user")
                .requestMatchers("/v1/customers/api/customers")
                .hasAnyRole("user")
                .requestMatchers(HttpMethod.POST, "/v1/jms/api/order")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/jms/api/order")
                .permitAll()
                .requestMatchers("/v1/order/api/customers/{customerId}/orders")
                .hasAnyRole("admin")
                .anyRequest()
                .authenticated();
        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);

        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);

        return http.build();
    }
}