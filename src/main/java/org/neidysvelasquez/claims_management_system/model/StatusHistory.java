package org.neidysvelasquez.claims_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name = "status_history")
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    @JsonBackReference
    private Claims claim;
}
