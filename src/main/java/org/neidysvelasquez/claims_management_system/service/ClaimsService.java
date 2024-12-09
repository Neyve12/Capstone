package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.ClaimRequestDTO;
import org.neidysvelasquez.claims_management_system.model.ClaimDocument;
import org.neidysvelasquez.claims_management_system.model.Claims;
import org.neidysvelasquez.claims_management_system.model.User;
import org.neidysvelasquez.claims_management_system.repository.ClaimsRepository;
import org.neidysvelasquez.claims_management_system.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
public class ClaimsService {

    private static final Logger logger = LoggerFactory.getLogger(ClaimsService.class);

    private final ClaimsRepository claimsRepository;
    private final UserRepository userRepository;

    public ClaimsService(ClaimsRepository claimsRepository, UserRepository userRepository) {
        this.claimsRepository = claimsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new claim.
     *
     * @param claim - the claim to create
     * @return the saved claim
     */
    public Claims createClaim(Claims claim) {
        logger.info("Attempting to create a new claim");

        //Validate userId is present
       if (claim.getUser() == null || claim.getUser().getId() == null) {
            throw new IllegalArgumentException("User is required for creating a claim.");
        }
       // Fetch the User object using userId
        Long userId = claim.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

       //Associate the User with Claim
        claim.setUser(user);

        //Save the claim
        Claims savedClaim = claimsRepository.save(claim);
        logger.info("Claim created successfully with ID: {}", savedClaim.getId());
        return savedClaim;

        }
    public Claims createClaim(ClaimRequestDTO dto) {
        logger.info("Attempting to create a new claim");

        // Fetch the User object using userId
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + dto.getUserId() + " not found."));

        // Create a new Claims object
        Claims claim = new Claims();
        claim.setUser(user);
        claim.setDescription(dto.getDescription());
        claim.setStatus(dto.getStatus());

        // Save the claim
        Claims savedClaim = claimsRepository.save(claim);
        logger.info("Claim created successfully {}", savedClaim.getId());
        return savedClaim;
    }

    /**
     * Get all claims.
     *
     * @return list of all claims
     */
    public List<Claims> getAllClaims() {
        List<Claims> claims = claimsRepository.findAll();
        logger.info("Fetched {} claims from the database.", claims.size());
        return claims;
    }

    /**
     * Get a claim by its ID.
     *
     * @param id the ID of the claim
     * @return the claim if found
     */
    public Claims getClaimById(Long id) {
        return claimsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Claim not found."));
    }
    public List<Claims> getClaimsByUserId(Long userId) {
        return claimsRepository.findByUserId(userId);
    }


    /**
     * Update a claim.
     *
     * @param claim the updated claim object
     * @return the updated claim
     */
    @Transactional
    public Claims updateClaim(Claims claim) {
        logger.info("Attempting to update claim with ID: {}", claim.getId());

        Claims existingClaim = claimsRepository.findById(claim.getId())
                .orElseThrow(() -> new IllegalArgumentException("Claim with ID " + claim.getId() + " does not exist."));

        if (claim.getUser() == null || !userRepository.existsById(claim.getUser().getId())) {
            throw new IllegalArgumentException("Associated user does not exist.");
        }

        // Update fields
        existingClaim.setDescription(claim.getDescription());
        existingClaim.getClaimDocuments().clear();
        claim.getClaimDocuments().forEach(existingClaim::addClaimDocument);

        Claims updatedClaim = claimsRepository.save(existingClaim);
        logger.info("Claim with ID {} updated successfully.", updatedClaim.getId());
        return updatedClaim;
    }

    /**
     * Deletes a claim by its ID.
     *
     * @param id the ID of the claim to delete
     */
    public void deleteClaim(Long id) {
        logger.info("Attempting to delete claim with ID: {}", id);

        if (!claimsRepository.existsById(id)) {
            throw new IllegalArgumentException("Claim with ID " + id + " does not exist.");
        }

        claimsRepository.deleteById(id);
        logger.info("Claim with ID {} deleted successfully.", id);
    }
}
