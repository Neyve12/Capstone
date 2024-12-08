package org.neidysvelasquez.claims_management_system.repository;

import org.neidysvelasquez.claims_management_system.model.ClaimDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimDocumentRepository extends JpaRepository<ClaimDocument, Long> {

    /**
     * Find all documents associated with a specific claim by claim ID.
     *
     * @param claimId the ID of the claim
     * @return a list of documents associated with the given claim ID
     */
    List<ClaimDocument> findAllByClaim_Id(Long claimId);

    /**
     * Find a document by its file name.
     *
     * @param fileName the name of the file
     * @return the document with the specified file name
     */
    ClaimDocument findByFileName(String fileName);
}
