package com.example.renovations.config.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AuthConfig {
  @Autowired
  SecurityFilter securityFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .cors(Customizer.withDefaults()) // Needed for CORS with Spring Security
        .csrf(csrf -> csrf.disable())
        .formLogin(formLogin -> formLogin.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exception -> exception
          //.accessDeniedHandler(new CustomAccessDeniedHandler())
          .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
        .authorizeHttpRequests(authorize -> authorize
          .requestMatchers(HttpMethod.POST, "/api/v1/auth/signin").permitAll()
          .requestMatchers(HttpMethod.OPTIONS, "/api/v1/auth/signin").permitAll()
          .requestMatchers("/error").permitAll()
          .requestMatchers("/h2-console*").permitAll()
          .requestMatchers("/h2-console/*").permitAll()
          .anyRequest().authenticated()
        )
        .headers(headers -> headers.frameOptions(frameOptionsConfig -> {
          // For h2-console
          frameOptionsConfig.disable();
          // For h2-console
          frameOptionsConfig.sameOrigin();
        }))
        .addFilterBefore(securityFilter, AuthorizationFilter.class)
        .build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Sets the configuration for CORS with
   * `httpSecurity.cors(Customizer.withDefaults())`
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("authorization", "Content-Type"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}