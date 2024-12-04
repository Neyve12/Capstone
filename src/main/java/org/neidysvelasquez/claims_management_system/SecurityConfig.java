package org.neidysvelasquez.claims_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings.
 * Defines security rules for the application, such as disabling CSRF
 * and permitting all requests (for development purposes).
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the application's security filter chain.
     *
     * @param http the HttpSecurity object to customize security rules
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disables CSRF protection (not recommended for production)
                .authorizeHttpRequests() // Configures authorization rules
                .anyRequest().permitAll(); // Permits all requests without authentication
        return http.build();
    }
}
