package com.corretor.corretor.config;

import com.corretor.corretor.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // OPTIONS (CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // LOGIN
                        .requestMatchers("/usuarios/login").permitAll()

                        // CADASTRO USUÁRIO
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // IMÓVEIS PÚBLICOS
                        .requestMatchers(HttpMethod.GET,
                                "/imoveis/publicos")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/imoveis/publicos/buscar")
                        .permitAll()

                        // TODO RESTANTE PROTEGIDO

                        .requestMatchers(HttpMethod.POST, "/propostas/imovel/**").permitAll()

                        .anyRequest().authenticated()

                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}