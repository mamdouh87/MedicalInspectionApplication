package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.MedicalInsepctionRequests} entity.
 */
public class MedicalInsepctionRequestsDTO implements Serializable {
    
    private Long id;

    private String requestNumber;


    private Long personId;

    private Long biometricdataId;

    private Long licenseCategoryId;

    private Long transacionTypeId;

    private Long trafficUnitId;

    private Long statusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personsId) {
        this.personId = personsId;
    }

    public Long getBiometricdataId() {
        return biometricdataId;
    }

    public void setBiometricdataId(Long requestBiometricDataId) {
        this.biometricdataId = requestBiometricDataId;
    }

    public Long getLicenseCategoryId() {
        return licenseCategoryId;
    }

    public void setLicenseCategoryId(Long licenseCategoryId) {
        this.licenseCategoryId = licenseCategoryId;
    }

    public Long getTransacionTypeId() {
        return transacionTypeId;
    }

    public void setTransacionTypeId(Long transactionTypeId) {
        this.transacionTypeId = transactionTypeId;
    }

    public Long getTrafficUnitId() {
        return trafficUnitId;
    }

    public void setTrafficUnitId(Long trafficUnitsId) {
        this.trafficUnitId = trafficUnitsId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long requestStatusId) {
        this.statusId = requestStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInsepctionRequestsDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalInsepctionRequestsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInsepctionRequestsDTO{" +
            "id=" + getId() +
            ", requestNumber='" + getRequestNumber() + "'" +
            ", personId=" + getPersonId() +
            ", biometricdataId=" + getBiometricdataId() +
            ", licenseCategoryId=" + getLicenseCategoryId() +
            ", transacionTypeId=" + getTransacionTypeId() +
            ", trafficUnitId=" + getTrafficUnitId() +
            ", statusId=" + getStatusId() +
            "}";
    }
}
