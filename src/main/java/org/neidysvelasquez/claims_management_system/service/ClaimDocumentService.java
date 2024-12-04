package org.neidysvelasquez.claims_management_system.service;

import org.neidysvelasquez.claims_management_system.model.ClaimDocument;
import org.neidysvelasquez.claims_management_system.repository.ClaimDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing ClaimDocument entities.
 * Handles business logic for storing, retrieving, and deleting claim-related documents.
 */
@Service
public class ClaimDocumentService {

    private final ClaimDocumentRepository claimDocumentRepository;

    /**
     * Constructor for ClaimDocumentService.
     *
     * @param claimDocumentRepository the repository for managing ClaimDocument entities
     */
    public ClaimDocumentService(ClaimDocumentRepository claimDocumentRepository) {
        this.claimDocumentRepository = claimDocumentRepository;
    }

    /**
     * Saves a claim document to the database.
     *
     * @param document the document to save
     * @return the saved ClaimDocument entity
     */
    public ClaimDocument saveDocument(ClaimDocument document) {
        return claimDocumentRepository.save(document);
    }

    /**
     * Retrieves all claim documents from the database.
     *
     * @return a list of all ClaimDocument entities
     */
    public List<ClaimDocument> getAllDocuments() {
        return claimDocumentRepository.findAll();
    }

    /**
     * Retrieves all documents associated with a specific claim ID.
     *
     * @param claimId the ID of the claim whose documents are to be retrieved
     * @return a list of ClaimDocument entities associated with the specified claim
     */
    public List<ClaimDocument> getDocumentsByClaimId(Long claimId) {
        return claimDocumentRepository.findAllByClaim_Id(claimId);
    }

    /**
     * Deletes a claim document by its ID.
     *
     * @param id the ID of the document to delete
     */
    public void deleteDocument(Long id) {
        claimDocumentRepository.deleteById(id);
    }
}
