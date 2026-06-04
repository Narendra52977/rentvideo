package com.example.rentvideo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.rentvideo.jwt.JwtAuthenticationFilter;
import com.example.rentvideo.services.CustomUserDetailsService;
import com.example.rentvideo.services.JwtService;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
// @Bean
//     public JwtAuthenticationFilter jwtAuthenticationFilter(
//             JwtService jwtService,
//             CustomUserDetailsService userDetailsService) {
//         return new JwtAuthenticationFilter(jwtService, userDetailsService);
//     }

    @Bean
    @Order(1)
    public SecurityFilterChain jwtSecurityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtFilter) throws Exception {

        return http
                .securityMatcher(
                        "/videos/*/rent",
                        "/videos/*/return")
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/videos/*/rent")
                        .hasRole("CUSTOMER") // ✅ move role check here
                        .requestMatchers(HttpMethod.POST, "/videos/*/return")
                        .hasRole("CUSTOMER") // ✅ move role check here
                        .anyRequest().authenticated())
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class).exceptionHandling(ex -> ex
    .authenticationEntryPoint(
        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    .accessDeniedHandler(
        (request, response, accessDeniedException) -> 
            response.setStatus(HttpStatus.FORBIDDEN.value())))
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain basicAuthSecurityFilterChain(
            HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                        .requestMatchers("/error").permitAll()
                        .requestMatchers(
                                "/register",
                                "/login",
                                "/videos")
                        .permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/video")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/video/**")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/video/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http)
    // throws Exception {

    // return http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/register",
    // "/login","/videos").permitAll()
    // .requestMatchers(HttpMethod.POST,
    // "/video")
    // .hasRole("ADMIN")

    // .requestMatchers(HttpMethod.PUT,
    // "/video/**")
    // .hasRole("ADMIN")

    // .requestMatchers(HttpMethod.DELETE,
    // "/video/**")
    // .hasRole("ADMIN")
    // .anyRequest().authenticated()
    // )
    // .httpBasic(Customizer.withDefaults())
    // .build();
    // }

}
