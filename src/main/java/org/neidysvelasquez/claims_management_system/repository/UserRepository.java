package org.neidysvelasquez.claims_management_system.repository;

import org.neidysvelasquez.claims_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * Provides standard CRUD operations and custom query methods for users.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email of the user to search for
     * @return the user with the specified email, or null if no user is found
     */
    User findByEmail(String email);
}

