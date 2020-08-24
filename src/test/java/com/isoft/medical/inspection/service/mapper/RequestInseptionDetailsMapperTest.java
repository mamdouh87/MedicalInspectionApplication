package com.isoft.medical.inspection.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestInseptionDetailsMapperTest {

    private RequestInseptionDetailsMapper requestInseptionDetailsMapper;

    @BeforeEach
    public void setUp() {
        requestInseptionDetailsMapper = new RequestInseptionDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestInseptionDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestInseptionDetailsMapper.fromId(null)).isNull();
    }
}
