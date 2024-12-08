package org.neidysvelasquez.claims_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "claim_document")
public class ClaimDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    @JsonBackReference
    private Claims claim;

    public void setClaim(Claims claim) {
        this.claim = claim;
        if (claim != null && !claim.getClaimDocuments().contains(this)) {
            claim.getClaimDocuments().add(this); // Synchronize with parent
        }
    }
}
