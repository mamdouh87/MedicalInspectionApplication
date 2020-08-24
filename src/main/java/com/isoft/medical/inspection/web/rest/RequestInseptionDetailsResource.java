package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.service.RequestInseptionDetailsService;
import com.isoft.medical.inspection.web.rest.errors.BadRequestAlertException;
import com.isoft.medical.inspection.service.dto.RequestInseptionDetailsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.isoft.medical.inspection.domain.RequestInseptionDetails}.
 */
@RestController
@RequestMapping("/api")
public class RequestInseptionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(RequestInseptionDetailsResource.class);

    private static final String ENTITY_NAME = "requestInseptionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestInseptionDetailsService requestInseptionDetailsService;

    public RequestInseptionDetailsResource(RequestInseptionDetailsService requestInseptionDetailsService) {
        this.requestInseptionDetailsService = requestInseptionDetailsService;
    }

    /**
     * {@code POST  /request-inseption-details} : Create a new requestInseptionDetails.
     *
     * @param requestInseptionDetailsDTO the requestInseptionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestInseptionDetailsDTO, or with status {@code 400 (Bad Request)} if the requestInseptionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-inseption-details")
    public ResponseEntity<RequestInseptionDetailsDTO> createRequestInseptionDetails(@RequestBody RequestInseptionDetailsDTO requestInseptionDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save RequestInseptionDetails : {}", requestInseptionDetailsDTO);
        if (requestInseptionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestInseptionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestInseptionDetailsDTO result = requestInseptionDetailsService.save(requestInseptionDetailsDTO);
        return ResponseEntity.created(new URI("/api/request-inseption-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-inseption-details} : Updates an existing requestInseptionDetails.
     *
     * @param requestInseptionDetailsDTO the requestInseptionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestInseptionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the requestInseptionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestInseptionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-inseption-details")
    public ResponseEntity<RequestInseptionDetailsDTO> updateRequestInseptionDetails(@RequestBody RequestInseptionDetailsDTO requestInseptionDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update RequestInseptionDetails : {}", requestInseptionDetailsDTO);
        if (requestInseptionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestInseptionDetailsDTO result = requestInseptionDetailsService.save(requestInseptionDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestInseptionDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-inseption-details} : get all the requestInseptionDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestInseptionDetails in body.
     */
    @GetMapping("/request-inseption-details")
    public ResponseEntity<List<RequestInseptionDetailsDTO>> getAllRequestInseptionDetails(Pageable pageable) {
        log.debug("REST request to get a page of RequestInseptionDetails");
        Page<RequestInseptionDetailsDTO> page = requestInseptionDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-inseption-details/:id} : get the "id" requestInseptionDetails.
     *
     * @param id the id of the requestInseptionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestInseptionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-inseption-details/{id}")
    public ResponseEntity<RequestInseptionDetailsDTO> getRequestInseptionDetails(@PathVariable Long id) {
        log.debug("REST request to get RequestInseptionDetails : {}", id);
        Optional<RequestInseptionDetailsDTO> requestInseptionDetailsDTO = requestInseptionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestInseptionDetailsDTO);
    }

    /**
     * {@code DELETE  /request-inseption-details/:id} : delete the "id" requestInseptionDetails.
     *
     * @param id the id of the requestInseptionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-inseption-details/{id}")
    public ResponseEntity<Void> deleteRequestInseptionDetails(@PathVariable Long id) {
        log.debug("REST request to delete RequestInseptionDetails : {}", id);
        requestInseptionDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
