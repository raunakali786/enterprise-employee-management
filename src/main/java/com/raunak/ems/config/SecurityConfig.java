package com.raunak.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin123"))
//                .password("{noop}admin123")  Normal without ENCODING
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Allow both USER and ADMIN to GET
                        .requestMatchers(HttpMethod.GET, "/api/employees/**")
                        .hasAnyRole("USER", "ADMIN")

                        // Only ADMIN can modify data
                        .requestMatchers(HttpMethod.POST, "/api/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}