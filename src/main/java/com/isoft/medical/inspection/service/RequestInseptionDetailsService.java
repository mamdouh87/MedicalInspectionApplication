package com.isoft.medical.inspection.service;

import com.isoft.medical.inspection.domain.RequestInseptionDetails;
import com.isoft.medical.inspection.repository.RequestInseptionDetailsRepository;
import com.isoft.medical.inspection.service.dto.RequestInseptionDetailsDTO;
import com.isoft.medical.inspection.service.mapper.RequestInseptionDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RequestInseptionDetails}.
 */
@Service
@Transactional
public class RequestInseptionDetailsService {

    private final Logger log = LoggerFactory.getLogger(RequestInseptionDetailsService.class);

    private final RequestInseptionDetailsRepository requestInseptionDetailsRepository;

    private final RequestInseptionDetailsMapper requestInseptionDetailsMapper;

    public RequestInseptionDetailsService(RequestInseptionDetailsRepository requestInseptionDetailsRepository, RequestInseptionDetailsMapper requestInseptionDetailsMapper) {
        this.requestInseptionDetailsRepository = requestInseptionDetailsRepository;
        this.requestInseptionDetailsMapper = requestInseptionDetailsMapper;
    }

    /**
     * Save a requestInseptionDetails.
     *
     * @param requestInseptionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestInseptionDetailsDTO save(RequestInseptionDetailsDTO requestInseptionDetailsDTO) {
        log.debug("Request to save RequestInseptionDetails : {}", requestInseptionDetailsDTO);
        RequestInseptionDetails requestInseptionDetails = requestInseptionDetailsMapper.toEntity(requestInseptionDetailsDTO);
        requestInseptionDetails = requestInseptionDetailsRepository.save(requestInseptionDetails);
        return requestInseptionDetailsMapper.toDto(requestInseptionDetails);
    }

    /**
     * Get all the requestInseptionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestInseptionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestInseptionDetails");
        return requestInseptionDetailsRepository.findAll(pageable)
            .map(requestInseptionDetailsMapper::toDto);
    }


    /**
     * Get one requestInseptionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestInseptionDetailsDTO> findOne(Long id) {
        log.debug("Request to get RequestInseptionDetails : {}", id);
        return requestInseptionDetailsRepository.findById(id)
            .map(requestInseptionDetailsMapper::toDto);
    }

    /**
     * Delete the requestInseptionDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RequestInseptionDetails : {}", id);
        requestInseptionDetailsRepository.deleteById(id);
    }
}
