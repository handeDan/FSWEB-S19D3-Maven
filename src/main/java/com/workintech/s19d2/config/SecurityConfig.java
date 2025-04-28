package com.workintech.s19d2.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırakmak için
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .requestMatchers("/user/**")
                    .hasRole("USER")
                    .anyRequest()
                    .authenticated())
        .formLogin(withDefaults()) // Form tabanlı kimlik doğrulama
        .logout(withDefaults()); // Çıkış işlemleri

    return http.build();
  }
}
