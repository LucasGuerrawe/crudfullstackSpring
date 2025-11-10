package com.exemplo.crudmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class endpoint {

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails professor = User.withDefaultPasswordEncoder()
                .username("professor")
                .password("senhaProfessor")
                .roles("PROFESSOR")
                .build();

        UserDetails estudante = User.withDefaultPasswordEncoder()
                .username("estudante")
                .password("senhaEstudante")
                .roles("ESTUDANTE")
                .build();

        return new InMemoryUserDetailsManager(professor, estudante);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/api/cursos/**").authenticated()
              .requestMatchers("/pessoas/**").authenticated()
              .anyRequest().permitAll()
          )
          .httpBasic();
        return http.build();
    }
}
