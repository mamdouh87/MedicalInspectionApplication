package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.MedicalInsepctionRequests;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalInsepctionRequests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalInsepctionRequestsRepository extends JpaRepository<MedicalInsepctionRequests, Long> {
}
