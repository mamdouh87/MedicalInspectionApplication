package com.isoft.medical.inspection.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class RequestInseptionDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestInseptionDetails.class);
        RequestInseptionDetails requestInseptionDetails1 = new RequestInseptionDetails();
        requestInseptionDetails1.setId(1L);
        RequestInseptionDetails requestInseptionDetails2 = new RequestInseptionDetails();
        requestInseptionDetails2.setId(requestInseptionDetails1.getId());
        assertThat(requestInseptionDetails1).isEqualTo(requestInseptionDetails2);
        requestInseptionDetails2.setId(2L);
        assertThat(requestInseptionDetails1).isNotEqualTo(requestInseptionDetails2);
        requestInseptionDetails1.setId(null);
        assertThat(requestInseptionDetails1).isNotEqualTo(requestInseptionDetails2);
    }
}
