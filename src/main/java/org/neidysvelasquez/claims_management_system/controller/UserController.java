package org.neidysvelasquez.claims_management_system.controller;

import org.neidysvelasquez.claims_management_system.model.User;
import org.neidysvelasquez.claims_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing User entities.
 * Provides endpoints for creating, retrieving, and deleting users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService the service for managing User entities
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return a ResponseEntity containing the created User entity with HTTP status 200 (OK)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    /**
     * Retrieves all users.
     *
     * @return a ResponseEntity containing an iterable of all User entities with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the User entity, if found, with HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email of the user to retrieve
     * @return a ResponseEntity containing the User entity, if found, with HTTP status 200 (OK)
     */
    @GetMapping("/email")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

