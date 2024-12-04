package org.neidysvelasquez.claims_management_system.controller;

import org.neidysvelasquez.claims_management_system.model.ClaimDocument;
import org.neidysvelasquez.claims_management_system.service.ClaimDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing ClaimDocument entities.
 * Provides endpoints for uploading, retrieving, and deleting claim-related documents.
 */
@RestController
@RequestMapping("/api/documents")
public class ClaimDocumentController {

    private final ClaimDocumentService claimDocumentService;

    /**
     * Constructor for ClaimDocumentController.
     *
     * @param claimDocumentService the service for managing ClaimDocument entities
     */
    public ClaimDocumentController(ClaimDocumentService claimDocumentService) {
        this.claimDocumentService = claimDocumentService;
    }

    /**
     * Uploads a new document.
     *
     * @param document the document to upload
     * @return a ResponseEntity containing the saved ClaimDocument with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ClaimDocument> uploadDocument(@RequestBody ClaimDocument document) {
        ClaimDocument savedDocument = claimDocumentService.saveDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
    }

    /**
     * Retrieves all documents.
     *
     * @return a ResponseEntity containing a list of all ClaimDocument entities with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ClaimDocument>> getAllDocuments() {
        return ResponseEntity.ok(claimDocumentService.getAllDocuments());
    }

    /**
     * Retrieves documents associated with a specific claim ID.
     *
     * @param claimId the ID of the claim whose documents are to be retrieved
     * @return a ResponseEntity containing a list of ClaimDocument entities with HTTP status 200 (OK)
     */
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<ClaimDocument>> getDocumentsByClaimId(@PathVariable Long claimId) {
        return ResponseEntity.ok(claimDocumentService.getDocumentsByClaimId(claimId));
    }

    /**
     * Deletes a document by its ID.
     *
     * @param id the ID of the document to delete
     * @return a ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        claimDocumentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
