package org.neidysvelasquez.claims_management_system.repository;

import org.neidysvelasquez.claims_management_system.model.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Repository interface for managing Claims entities.
 * Provides standard CRUD operations and custom query methods for claims.
 */
@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Long> {
    // Custom query method to find claims by user ID
    List<Claims> findByUserId(Long userId);
}
