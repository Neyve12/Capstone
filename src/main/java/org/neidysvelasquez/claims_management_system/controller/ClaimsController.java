package org.neidysvelasquez.claims_management_system.controller;

import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.service.ClaimsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Claims entities.
 * Provides endpoints for creating, retrieving, and deleting claims.
 */
@RestController
@RequestMapping("/api/claims")
public class ClaimsController {

    private final ClaimsService claimsService;

    /**
     * Constructor for ClaimsController.
     *
     * @param claimsService the service for managing Claims entities
     */
    public ClaimsController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    /**
     * Creates a new claim.
     *
     * @param claim the claim to be created
     * @return a ResponseEntity containing the created Claims entity
     */
    @PostMapping
    public ResponseEntity<Claims> createClaim(@RequestBody Claims claim) {
        return ResponseEntity.ok(claimsService.createClaim(claim));
    }

    /**
     * Retrieves all claims.
     *
     * @return a ResponseEntity containing a list of all Claims entities
     */
    @GetMapping
    public ResponseEntity<List<Claims>> getAllClaims() {
        return ResponseEntity.ok(claimsService.getAllClaims());
    }

    /**
     * Retrieves all claims associated with a specific user ID.
     *
     * @param userId the ID of the user whose claims are to be retrieved
     * @return a ResponseEntity containing a list of Claims entities
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Claims>> getClaimsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(claimsService.getClaimsByUserId(userId));
    }

    /**
     * Retrieves a claim by its ID.
     *
     * @param id the ID of the claim to be retrieved
     * @return a ResponseEntity containing the Claims entity, if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Claims>> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimsService.getClaimById(id));
    }

    /**
     * Deletes a claim by its ID.
     *
     * @param id the ID of the claim to be deleted
     * @return a ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimsService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}

