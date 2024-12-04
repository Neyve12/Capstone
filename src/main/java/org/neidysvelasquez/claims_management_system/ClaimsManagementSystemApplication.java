package org.neidysvelasquez.claims_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Claims Management System application.
 * This class bootstraps the Spring Boot application.
 */
@SpringBootApplication
public class ClaimsManagementSystemApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ClaimsManagementSystemApplication.class, args);
    }

}

