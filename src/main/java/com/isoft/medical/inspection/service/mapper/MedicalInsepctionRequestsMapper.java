package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.MedicalInsepctionRequestsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalInsepctionRequests} and its DTO {@link MedicalInsepctionRequestsDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonsMapper.class, RequestBiometricDataMapper.class, LicenseCategoryMapper.class, TransactionTypeMapper.class, TrafficUnitsMapper.class, RequestStatusMapper.class})
public interface MedicalInsepctionRequestsMapper extends EntityMapper<MedicalInsepctionRequestsDTO, MedicalInsepctionRequests> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "biometricdata.id", target = "biometricdataId")
    @Mapping(source = "licenseCategory.id", target = "licenseCategoryId")
    @Mapping(source = "transacionType.id", target = "transacionTypeId")
    @Mapping(source = "trafficUnit.id", target = "trafficUnitId")
    @Mapping(source = "status.id", target = "statusId")
    MedicalInsepctionRequestsDTO toDto(MedicalInsepctionRequests medicalInsepctionRequests);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "biometricdataId", target = "biometricdata")
    @Mapping(source = "licenseCategoryId", target = "licenseCategory")
    @Mapping(source = "transacionTypeId", target = "transacionType")
    @Mapping(source = "trafficUnitId", target = "trafficUnit")
    @Mapping(source = "statusId", target = "status")
    @Mapping(target = "medicalInsepctionRequests", ignore = true)
    @Mapping(target = "removeMedicalInsepctionRequests", ignore = true)
    MedicalInsepctionRequests toEntity(MedicalInsepctionRequestsDTO medicalInsepctionRequestsDTO);

    default MedicalInsepctionRequests fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalInsepctionRequests medicalInsepctionRequests = new MedicalInsepctionRequests();
        medicalInsepctionRequests.setId(id);
        return medicalInsepctionRequests;
    }
}
