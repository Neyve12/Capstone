package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.model.User;
import org.neidysvelasquez.claims_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing User entities.
 * Handles business logic for creating, retrieving, and deleting users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository the repository for managing User entities
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates or saves a user in the database.
     *
     * @param user the user to save
     * @return the saved User entity
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return an iterable of all User entities
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the ID of the user to retrieve
     * @return an Optional containing the User entity if found, or empty if not found
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email of the user to retrieve
     * @return an Optional containing the User entity if found, or empty if not found
     */
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
