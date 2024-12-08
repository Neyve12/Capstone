package org.neidysvelasquez.claims_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "claims")
public class Claims {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is required")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Status is required")
    @Column(name = "status")
    private String status = "Pending"; // Default value


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ClaimDocument> claimDocuments= new ArrayList<>();

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<StatusHistory> statusHistories = new ArrayList<>();

    @NotNull
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Add a method to add a document
    public void addClaimDocument(ClaimDocument document) {
        claimDocuments.add(document);
        document.setClaim(this); // Maintain the bidirectional relationship
    }

    // Add a method to remove a document
    public void removeClaimDocument(ClaimDocument document) {
        claimDocuments.remove(document);
        document.setClaim(null); // Break the bidirectional relationship
    }

    // method to add a status history
    public void addStatusHistory(StatusHistory history) {
        statusHistories.add(history);
        history.setClaim(this); // Maintain the bidirectional relationship
    }

    // method to remove a status history
    public void removeStatusHistory(StatusHistory history) {
        statusHistories.remove(history);
        history.setClaim(null); // Break the bidirectional relationship
    }

}

