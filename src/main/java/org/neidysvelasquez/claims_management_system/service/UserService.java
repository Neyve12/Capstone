package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.model.User;
import org.neidysvelasquez.claims_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user along with associated claims.
     *
     * @param user the user to be created
     * @return the created user
     */
    public User createUser(User user) {
        // Example of creating claims and associating them with the user
        Claims claim1 = new Claims();
        claim1.setDescription("Claim for product defect");
        claim1.setStatus("Pending");
        claim1.setUser(user); // Associate the claim with the user

        Claims claim2 = new Claims();
        claim2.setDescription("Claim for shipping delay");
        claim2.setStatus("Pending");
        claim2.setUser(user); // Associate the claim with the user

        // Add claims to the user's claims list
        user.setClaims(List.of(claim1, claim2));

        // Save the user (and claims will be saved automatically due to CascadeType.ALL)
        return userRepository.save(user);
    }

    /**
     * Retrieves all users.
     *
     * @return the list of users
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

        /**
         * Retrieves a user by their ID.
         * Returns null if no user is found.
         *
         * @param id the ID of the user
         * @return the user if found, or null if not
         */
        public User getUserById(Long id) {
            return userRepository.findById(id).orElse(null);
        }
    }