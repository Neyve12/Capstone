package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.repository.ClaimsRepository;
import org.neidysvelasquez.claims_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimsService {

    @Autowired
    private ClaimsRepository claimsRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new claim
     *
     * @param claim - the claim to create
     * @return the saved claim
     * @throws IllegalArgumentException if the user is missing or not found
     */
    public Claims createClaim(Claims claim) {
        // Validate that the user is provided
        if (claim.getUser() == null || claim.getUser().getId() == null) {
            throw new IllegalArgumentException("User is required for creating a claim");
        }

        // Ensure the user exists in the database
        userRepository.findById(claim.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Save and return the claim
        return claimsRepository.save(claim);
    }


    // Get all claims
    public List<Claims> getAllClaims() {
        return claimsRepository.findAll();
    }

    // Get claims by user ID
    public List<Claims> getClaimsByUserId(Long userId) {
        return claimsRepository.findByUserId(userId);
    }

    // Get a claim by ID
    public Optional<Claims> getClaimById(Long id) {
        return claimsRepository.findById(id);
    }

    // Delete a claim
    public void deleteClaim(Long id) {
        claimsRepository.deleteById(id);
    }

    //Update claim
        public Claims updateClaim(Claims claim) {
            // Validate the claim exists
            if (!claimsRepository.existsById(claim.getId())) {
                throw new IllegalArgumentException("Claim with ID " + claim.getId() + " does not exist");
            }
            return claimsRepository.save(claim);
        }
    }