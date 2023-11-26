package com.devJaewon.springSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 로그인 인증을 위한 In-Momery User 객체 주석처리
    // @Bean
    // InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    //     String password = passwordEncoder().encode("1234");

    //     UserDetails user = User.withUsername("user")
    //             .password(password)
    //             .roles("USER")
    //             .build();

    //     UserDetails admin = User.withUsername("admin")
    //             .password(password)
    //             .roles("ADMIN")
    //             .build();

    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/account/create").permitAll()
                        .requestMatchers("/account/login").permitAll()
                        .requestMatchers("/mypage").hasRole("USER")
                        .requestMatchers("/setting").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }
}
