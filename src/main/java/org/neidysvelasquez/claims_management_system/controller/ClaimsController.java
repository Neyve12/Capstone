package org.neidysvelasquez.claims_management_system.controller;

import jakarta.validation.Valid;
import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.service.ClaimsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Claims entities.
 * Provides endpoints for creating, retrieving, updating, and deleting claims.
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
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8"},
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8"}
    )
    public ResponseEntity<Claims> createClaim(@Valid @RequestBody Claims claim) {
        Claims createdClaim = claimsService.createClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClaim);
    }

    /**
     * Retrieves all claims.
     *
     * @return a ResponseEntity containing a list of all Claims entities
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Claims>> getAllClaims() {
        List<Claims> claims = claimsService.getAllClaims();
        return ResponseEntity.ok(claims);
    }

    /**
     * Retrieves all claims associated with a specific user ID.
     *
     * @param userId the ID of the user whose claims are to be retrieved
     * @return a ResponseEntity containing a list of Claims entities
     */
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Claims>> getClaimsByUserId(@PathVariable Long userId) {
        List<Claims> claims = claimsService.getClaimsByUserId(userId);
        return ResponseEntity.ok(claims);
    }

    /**
     * Retrieves a claim by its ID.
     *
     * @param id the ID of the claim to be retrieved
     * @return a ResponseEntity containing the Claims entity, if found
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Claims> getClaimById(@PathVariable Long id) {
        Optional<Claims> claimOptional = Optional.ofNullable(claimsService.getClaimById(id));
        return claimOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing claim by its ID.
     *
     * @param id           the ID of the claim to be updated
     * @param updatedClaim the updated claim details
     * @return a ResponseEntity containing the updated Claims entity
     */
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8"},
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8"}
    )
    public ResponseEntity<Claims> updateClaim(@PathVariable Long id, @RequestBody Claims updatedClaim) {
        updatedClaim.setId(id); // Ensure the ID is set
        Claims claim = claimsService.updateClaim(updatedClaim);
        return ResponseEntity.ok(claim);
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


