package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.RequestInseptionDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RequestInseptionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestInseptionDetailsRepository extends JpaRepository<RequestInseptionDetails, Long> {
}
