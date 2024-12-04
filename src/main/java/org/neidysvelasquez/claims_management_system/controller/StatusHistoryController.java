package org.neidysvelasquez.claims_management_system.controller;

import org.neidysvelasquez.claims_management_system.model.StatusHistory;
import org.neidysvelasquez.claims_management_system.service.StatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status-history")
public class StatusHistoryController {

    @Autowired
    private StatusHistoryService statusHistoryService;

    /**
     * Create a new status history entry
     *
     * @param statusHistory the status history entry to create
     * @return the created status history entry
     */
    @PostMapping
    public ResponseEntity<StatusHistory> createStatusHistory(@RequestBody StatusHistory statusHistory) {
        StatusHistory savedStatusHistory = statusHistoryService.saveStatusHistory(statusHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStatusHistory);
    }

    /**
     * Retrieve all status history records
     *
     * @return a list of all status history records
     */
    @GetMapping
    public ResponseEntity<List<StatusHistory>> getAllStatusHistories() {
        List<StatusHistory> histories = statusHistoryService.getAllStatusHistories();
        return ResponseEntity.ok(histories);
    }

    /**
     * Retrieve status history by claim ID
     *
     * @param claimId the ID of the claim whose status history is to be retrieved
     * @return a list of status history entries for the specified claim
     */
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<StatusHistory>> getStatusHistoriesByClaimId(@PathVariable Long claimId) {
        List<StatusHistory> histories = statusHistoryService.getStatusHistoriesByClaimId(claimId);
        return ResponseEntity.ok(histories);
    }

    /**
     * Delete a status history entry by ID
     *
     * @param id the ID of the status history entry to delete
     * @return a response indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatusHistory(@PathVariable Long id) {
        statusHistoryService.deleteStatusHistory(id);
        return ResponseEntity.noContent().build();
    }
}
