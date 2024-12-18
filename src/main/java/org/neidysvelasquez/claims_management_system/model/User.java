package org.neidysvelasquez.claims_management_system.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user in the Claims Management System.
 * A user can file multiple claims and is identified by a unique email.
 */
@Entity
@Getter
@Setter
public class User {

    /**
     * The unique identifier for the user, auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate IDs
    private Long id;

    /**
     * The name of the user.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * The email address of the user.
     * Must be a valid email format and is mandatory.
     */
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * The list of claims filed by the user.
     * Represents a one-to-many relationship with the Claims entity.
     * Cascade operations ensure claims are managed automatically with user actions.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // One user has many claims
    @JsonManagedReference
    private List<Claims> claims;
}

