package com.isoft.medical.inspection.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.medical.inspection.web.rest.TestUtil;

public class RequestInseptionDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestInseptionDetailsDTO.class);
        RequestInseptionDetailsDTO requestInseptionDetailsDTO1 = new RequestInseptionDetailsDTO();
        requestInseptionDetailsDTO1.setId(1L);
        RequestInseptionDetailsDTO requestInseptionDetailsDTO2 = new RequestInseptionDetailsDTO();
        assertThat(requestInseptionDetailsDTO1).isNotEqualTo(requestInseptionDetailsDTO2);
        requestInseptionDetailsDTO2.setId(requestInseptionDetailsDTO1.getId());
        assertThat(requestInseptionDetailsDTO1).isEqualTo(requestInseptionDetailsDTO2);
        requestInseptionDetailsDTO2.setId(2L);
        assertThat(requestInseptionDetailsDTO1).isNotEqualTo(requestInseptionDetailsDTO2);
        requestInseptionDetailsDTO1.setId(null);
        assertThat(requestInseptionDetailsDTO1).isNotEqualTo(requestInseptionDetailsDTO2);
    }
}
