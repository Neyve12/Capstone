package org.neidysvelasquez.claims_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a document associated with a claim in the Claims Management System.
 * A document can be an image, PDF, or other file type uploaded as supporting evidence for a claim.
 */
@Entity
@Getter
@Setter
public class ClaimDocument {

    /**
     * The unique identifier for the document.
     * Auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the uploaded file.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "File name is required")
    private String fileName;

    /**
     * The type of the uploaded file (e.g., PDF, JPEG).
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "File type is required")
    private String fileType;

    /**
     * The binary data of the uploaded file.
     * Stored as a large object (LOB) in the database.
     */
    @Lob
    private byte[] data;

    /**
     * The claim associated with this document.
     * Represents a many-to-one relationship with the `Claims` entity.
     */
    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claims claim;

}
