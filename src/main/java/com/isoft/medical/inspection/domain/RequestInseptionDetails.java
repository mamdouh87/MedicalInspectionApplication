package com.isoft.medical.inspection.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A RequestInseptionDetails.
 */
@Entity
@Table(name = "request_inseption_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RequestInseptionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "rigth_eye")
    private String rigthEye;

    @Column(name = "left_eye")
    private String leftEye;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "doctor_comments")
    private String doctorComments;

    @OneToOne
    @JoinColumn(unique = true)
    private MedicalCondition medicalCondition;

    @OneToOne
    @JoinColumn(unique = true)
    private InspectionType inspectionType;

    @OneToOne
    @JoinColumn(unique = true)
    private InspectionResult inspectionResult;

    @ManyToOne
    @JsonIgnoreProperties(value = "medicalInsepctionRequests", allowSetters = true)
    private MedicalInsepctionRequests medicalInsepctionRequests;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRigthEye() {
        return rigthEye;
    }

    public RequestInseptionDetails rigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
        return this;
    }

    public void setRigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public RequestInseptionDetails leftEye(String leftEye) {
        this.leftEye = leftEye;
        return this;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public RequestInseptionDetails bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public RequestInseptionDetails doctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
        return this;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    public RequestInseptionDetails medicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
        return this;
    }

    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public RequestInseptionDetails inspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
        return this;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    public InspectionResult getInspectionResult() {
        return inspectionResult;
    }

    public RequestInseptionDetails inspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
        return this;
    }

    public void setInspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public MedicalInsepctionRequests getMedicalInsepctionRequests() {
        return medicalInsepctionRequests;
    }

    public RequestInseptionDetails medicalInsepctionRequests(MedicalInsepctionRequests medicalInsepctionRequests) {
        this.medicalInsepctionRequests = medicalInsepctionRequests;
        return this;
    }

    public void setMedicalInsepctionRequests(MedicalInsepctionRequests medicalInsepctionRequests) {
        this.medicalInsepctionRequests = medicalInsepctionRequests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestInseptionDetails)) {
            return false;
        }
        return id != null && id.equals(((RequestInseptionDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestInseptionDetails{" +
            "id=" + getId() +
            ", rigthEye='" + getRigthEye() + "'" +
            ", leftEye='" + getLeftEye() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            "}";
    }
}
