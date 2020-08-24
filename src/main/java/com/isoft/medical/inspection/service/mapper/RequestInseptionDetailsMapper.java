package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.RequestInseptionDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestInseptionDetails} and its DTO {@link RequestInseptionDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalConditionMapper.class, InspectionTypeMapper.class, InspectionResultMapper.class, MedicalInsepctionRequestsMapper.class})
public interface RequestInseptionDetailsMapper extends EntityMapper<RequestInseptionDetailsDTO, RequestInseptionDetails> {

    @Mapping(source = "medicalCondition.id", target = "medicalConditionId")
    @Mapping(source = "inspectionType.id", target = "inspectionTypeId")
    @Mapping(source = "inspectionResult.id", target = "inspectionResultId")
    @Mapping(source = "medicalInsepctionRequests.id", target = "medicalInsepctionRequestsId")
    RequestInseptionDetailsDTO toDto(RequestInseptionDetails requestInseptionDetails);

    @Mapping(source = "medicalConditionId", target = "medicalCondition")
    @Mapping(source = "inspectionTypeId", target = "inspectionType")
    @Mapping(source = "inspectionResultId", target = "inspectionResult")
    @Mapping(source = "medicalInsepctionRequestsId", target = "medicalInsepctionRequests")
    RequestInseptionDetails toEntity(RequestInseptionDetailsDTO requestInseptionDetailsDTO);

    default RequestInseptionDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestInseptionDetails requestInseptionDetails = new RequestInseptionDetails();
        requestInseptionDetails.setId(id);
        return requestInseptionDetails;
    }
}
