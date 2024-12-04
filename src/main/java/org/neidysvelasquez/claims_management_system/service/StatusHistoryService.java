package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.model.StatusHistory;
import org.neidysvelasquez.claims_management_system.repository.StatusHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing StatusHistory entities.
 * Handles business logic for storing, retrieving, and deleting status history records.
 */
@Service
public class StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;

    /**
     * Constructor for StatusHistoryService.
     *
     * @param statusHistoryRepository the repository for managing StatusHistory entities
     */
    public StatusHistoryService(StatusHistoryRepository statusHistoryRepository) {
        this.statusHistoryRepository = statusHistoryRepository;
    }

    /**
     * Saves a status history record to the database.
     *
     * @param statusHistory the status history record to save
     * @return the saved StatusHistory entity
     */
    public StatusHistory saveStatusHistory(StatusHistory statusHistory) {
        return statusHistoryRepository.save(statusHistory);
    }

    /**
     * Retrieves all status history records from the database.
     *
     * @return a list of all StatusHistory entities
     */
    public List<StatusHistory> getAllStatusHistories() {
        return statusHistoryRepository.findAll();
    }

    /**
     * Retrieves all status history records associated with a specific claim ID.
     *
     * @param claimId the ID of the claim whose status history records are to be retrieved
     * @return a list of StatusHistory entities associated with the specified claim
     */
    public List<StatusHistory> getStatusHistoriesByClaimId(Long claimId) {
        return statusHistoryRepository.findAllByClaim_Id(claimId);
    }

    /**
     * Deletes a status history record by its ID.
     *
     * @param id the ID of the status history record to delete
     */
    public void deleteStatusHistory(Long id) {
        statusHistoryRepository.deleteById(id);
    }
}
