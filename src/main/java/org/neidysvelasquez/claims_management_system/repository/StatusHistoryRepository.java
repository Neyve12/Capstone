package org.neidysvelasquez.claims_management_system.repository;

import org.neidysvelasquez.claims_management_system.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing StatusHistory entities.
 * Provides standard CRUD operations and custom query methods for status history records.
 */
@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

    /**
     * Finds all status history records associated with a specific claim.
     *
     * @param claimId the ID of the claim whose status history records are to be retrieved
     * @return a list of status history records associated with the specified claim
     */
    List<StatusHistory> findAllByClaim_Id(Long claimId);
}

