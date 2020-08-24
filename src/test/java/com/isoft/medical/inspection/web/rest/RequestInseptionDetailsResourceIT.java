package com.isoft.medical.inspection.web.rest;

import com.isoft.medical.inspection.MedicalInspectionApplicationApp;
import com.isoft.medical.inspection.domain.RequestInseptionDetails;
import com.isoft.medical.inspection.repository.RequestInseptionDetailsRepository;
import com.isoft.medical.inspection.service.RequestInseptionDetailsService;
import com.isoft.medical.inspection.service.dto.RequestInseptionDetailsDTO;
import com.isoft.medical.inspection.service.mapper.RequestInseptionDetailsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RequestInseptionDetailsResource} REST controller.
 */
@SpringBootTest(classes = MedicalInspectionApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestInseptionDetailsResourceIT {

    private static final String DEFAULT_RIGTH_EYE = "AAAAAAAAAA";
    private static final String UPDATED_RIGTH_EYE = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_EYE = "AAAAAAAAAA";
    private static final String UPDATED_LEFT_EYE = "BBBBBBBBBB";

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private RequestInseptionDetailsRepository requestInseptionDetailsRepository;

    @Autowired
    private RequestInseptionDetailsMapper requestInseptionDetailsMapper;

    @Autowired
    private RequestInseptionDetailsService requestInseptionDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestInseptionDetailsMockMvc;

    private RequestInseptionDetails requestInseptionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestInseptionDetails createEntity(EntityManager em) {
        RequestInseptionDetails requestInseptionDetails = new RequestInseptionDetails()
            .rigthEye(DEFAULT_RIGTH_EYE)
            .leftEye(DEFAULT_LEFT_EYE)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .doctorComments(DEFAULT_DOCTOR_COMMENTS);
        return requestInseptionDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestInseptionDetails createUpdatedEntity(EntityManager em) {
        RequestInseptionDetails requestInseptionDetails = new RequestInseptionDetails()
            .rigthEye(UPDATED_RIGTH_EYE)
            .leftEye(UPDATED_LEFT_EYE)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        return requestInseptionDetails;
    }

    @BeforeEach
    public void initTest() {
        requestInseptionDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestInseptionDetails() throws Exception {
        int databaseSizeBeforeCreate = requestInseptionDetailsRepository.findAll().size();
        // Create the RequestInseptionDetails
        RequestInseptionDetailsDTO requestInseptionDetailsDTO = requestInseptionDetailsMapper.toDto(requestInseptionDetails);
        restRequestInseptionDetailsMockMvc.perform(post("/api/request-inseption-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestInseptionDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the RequestInseptionDetails in the database
        List<RequestInseptionDetails> requestInseptionDetailsList = requestInseptionDetailsRepository.findAll();
        assertThat(requestInseptionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        RequestInseptionDetails testRequestInseptionDetails = requestInseptionDetailsList.get(requestInseptionDetailsList.size() - 1);
        assertThat(testRequestInseptionDetails.getRigthEye()).isEqualTo(DEFAULT_RIGTH_EYE);
        assertThat(testRequestInseptionDetails.getLeftEye()).isEqualTo(DEFAULT_LEFT_EYE);
        assertThat(testRequestInseptionDetails.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testRequestInseptionDetails.getDoctorComments()).isEqualTo(DEFAULT_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void createRequestInseptionDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestInseptionDetailsRepository.findAll().size();

        // Create the RequestInseptionDetails with an existing ID
        requestInseptionDetails.setId(1L);
        RequestInseptionDetailsDTO requestInseptionDetailsDTO = requestInseptionDetailsMapper.toDto(requestInseptionDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestInseptionDetailsMockMvc.perform(post("/api/request-inseption-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestInseptionDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestInseptionDetails in the database
        List<RequestInseptionDetails> requestInseptionDetailsList = requestInseptionDetailsRepository.findAll();
        assertThat(requestInseptionDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequestInseptionDetails() throws Exception {
        // Initialize the database
        requestInseptionDetailsRepository.saveAndFlush(requestInseptionDetails);

        // Get all the requestInseptionDetailsList
        restRequestInseptionDetailsMockMvc.perform(get("/api/request-inseption-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestInseptionDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].rigthEye").value(hasItem(DEFAULT_RIGTH_EYE)))
            .andExpect(jsonPath("$.[*].leftEye").value(hasItem(DEFAULT_LEFT_EYE)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].doctorComments").value(hasItem(DEFAULT_DOCTOR_COMMENTS)));
    }
    
    @Test
    @Transactional
    public void getRequestInseptionDetails() throws Exception {
        // Initialize the database
        requestInseptionDetailsRepository.saveAndFlush(requestInseptionDetails);

        // Get the requestInseptionDetails
        restRequestInseptionDetailsMockMvc.perform(get("/api/request-inseption-details/{id}", requestInseptionDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestInseptionDetails.getId().intValue()))
            .andExpect(jsonPath("$.rigthEye").value(DEFAULT_RIGTH_EYE))
            .andExpect(jsonPath("$.leftEye").value(DEFAULT_LEFT_EYE))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP))
            .andExpect(jsonPath("$.doctorComments").value(DEFAULT_DOCTOR_COMMENTS));
    }
    @Test
    @Transactional
    public void getNonExistingRequestInseptionDetails() throws Exception {
        // Get the requestInseptionDetails
        restRequestInseptionDetailsMockMvc.perform(get("/api/request-inseption-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestInseptionDetails() throws Exception {
        // Initialize the database
        requestInseptionDetailsRepository.saveAndFlush(requestInseptionDetails);

        int databaseSizeBeforeUpdate = requestInseptionDetailsRepository.findAll().size();

        // Update the requestInseptionDetails
        RequestInseptionDetails updatedRequestInseptionDetails = requestInseptionDetailsRepository.findById(requestInseptionDetails.getId()).get();
        // Disconnect from session so that the updates on updatedRequestInseptionDetails are not directly saved in db
        em.detach(updatedRequestInseptionDetails);
        updatedRequestInseptionDetails
            .rigthEye(UPDATED_RIGTH_EYE)
            .leftEye(UPDATED_LEFT_EYE)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .doctorComments(UPDATED_DOCTOR_COMMENTS);
        RequestInseptionDetailsDTO requestInseptionDetailsDTO = requestInseptionDetailsMapper.toDto(updatedRequestInseptionDetails);

        restRequestInseptionDetailsMockMvc.perform(put("/api/request-inseption-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestInseptionDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the RequestInseptionDetails in the database
        List<RequestInseptionDetails> requestInseptionDetailsList = requestInseptionDetailsRepository.findAll();
        assertThat(requestInseptionDetailsList).hasSize(databaseSizeBeforeUpdate);
        RequestInseptionDetails testRequestInseptionDetails = requestInseptionDetailsList.get(requestInseptionDetailsList.size() - 1);
        assertThat(testRequestInseptionDetails.getRigthEye()).isEqualTo(UPDATED_RIGTH_EYE);
        assertThat(testRequestInseptionDetails.getLeftEye()).isEqualTo(UPDATED_LEFT_EYE);
        assertThat(testRequestInseptionDetails.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testRequestInseptionDetails.getDoctorComments()).isEqualTo(UPDATED_DOCTOR_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestInseptionDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestInseptionDetailsRepository.findAll().size();

        // Create the RequestInseptionDetails
        RequestInseptionDetailsDTO requestInseptionDetailsDTO = requestInseptionDetailsMapper.toDto(requestInseptionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestInseptionDetailsMockMvc.perform(put("/api/request-inseption-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestInseptionDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestInseptionDetails in the database
        List<RequestInseptionDetails> requestInseptionDetailsList = requestInseptionDetailsRepository.findAll();
        assertThat(requestInseptionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestInseptionDetails() throws Exception {
        // Initialize the database
        requestInseptionDetailsRepository.saveAndFlush(requestInseptionDetails);

        int databaseSizeBeforeDelete = requestInseptionDetailsRepository.findAll().size();

        // Delete the requestInseptionDetails
        restRequestInseptionDetailsMockMvc.perform(delete("/api/request-inseption-details/{id}", requestInseptionDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestInseptionDetails> requestInseptionDetailsList = requestInseptionDetailsRepository.findAll();
        assertThat(requestInseptionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
