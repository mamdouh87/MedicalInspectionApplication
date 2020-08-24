package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.RequestInseptionDetails} entity.
 */
public class RequestInseptionDetailsDTO implements Serializable {
    
    private Long id;

    private String rigthEye;

    private String leftEye;

    private String bloodGroup;

    private String doctorComments;


    private Long medicalConditionId;

    private Long inspectionTypeId;

    private Long inspectionResultId;

    private Long medicalInsepctionRequestsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRigthEye() {
        return rigthEye;
    }

    public void setRigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public Long getMedicalConditionId() {
        return medicalConditionId;
    }

    public void setMedicalConditionId(Long medicalConditionId) {
        this.medicalConditionId = medicalConditionId;
    }

    public Long getInspectionTypeId() {
        return inspectionTypeId;
    }

    public void setInspectionTypeId(Long inspectionTypeId) {
        this.inspectionTypeId = inspectionTypeId;
    }

    public Long getInspectionResultId() {
        return inspectionResultId;
    }

    public void setInspectionResultId(Long inspectionResultId) {
        this.inspectionResultId = inspectionResultId;
    }

    public Long getMedicalInsepctionRequestsId() {
        return medicalInsepctionRequestsId;
    }

    public void setMedicalInsepctionRequestsId(Long medicalInsepctionRequestsId) {
        this.medicalInsepctionRequestsId = medicalInsepctionRequestsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestInseptionDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((RequestInseptionDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestInseptionDetailsDTO{" +
            "id=" + getId() +
            ", rigthEye='" + getRigthEye() + "'" +
            ", leftEye='" + getLeftEye() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            ", medicalConditionId=" + getMedicalConditionId() +
            ", inspectionTypeId=" + getInspectionTypeId() +
            ", inspectionResultId=" + getInspectionResultId() +
            ", medicalInsepctionRequestsId=" + getMedicalInsepctionRequestsId() +
            "}";
    }
}
