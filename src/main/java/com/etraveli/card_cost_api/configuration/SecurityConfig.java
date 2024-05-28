package com.etraveli.card_cost_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/card-cost-api/rest/v1/payment-cards-cost").permitAll()
                        .requestMatchers(HttpMethod.GET, "/card-cost-api/rest/v1/clearing-costs", "/card-cost-api/rest/v1/clearing-costs/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/card-cost-api/rest/v1/clearing-costs/insert").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/card-cost-api/rest/v1/clearing-costs/update").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/card-cost-api/rest/v1/clearing-costs/{id}").authenticated()
                        .anyRequest().denyAll())
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER_ROLE")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
