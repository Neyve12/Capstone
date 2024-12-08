/*package org.neidysvelasquez.claims_management_system;

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
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/api/users/", "/css/", "/js/", "/templates/").permitAll() // Publicly accessible paths
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .disable() // Disable form-based login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirect to home after logout
                        .permitAll()
                );

        return http.build();
    }
}
*/
