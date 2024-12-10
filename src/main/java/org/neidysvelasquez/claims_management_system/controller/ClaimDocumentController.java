package org.neidysvelasquez.claims_management_system.controller;

import org.neidysvelasquez.claims_management_system.model.ClaimDocument;
import org.neidysvelasquez.claims_management_system.service.ClaimDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * REST controller for managing ClaimDocument entities.
 * Provides endpoints for uploading, retrieving, and deleting claim-related documents.
 */
@RestController
@RequestMapping("/api")
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
     *
     */
        @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
            try {
                // Create the upload directory if it doesn't exist
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                Files.createDirectories(Paths.get(uploadDir));

                // Save the file to the upload directory
                String filePath = uploadDir + "/" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(filePath));

                // Return a success response
                return ResponseEntity.ok("File uploaded successfully: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error uploading file: " + e.getMessage());
            }
        }
    }


