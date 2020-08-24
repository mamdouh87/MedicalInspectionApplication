package com.isoft.medical.inspection.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MedicalInsepctionRequests.
 */
@Entity
@Table(name = "medical_insepction_requests")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalInsepctionRequests extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "request_number")
    private String requestNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Persons person;

    @OneToOne
    @JoinColumn(unique = true)
    private RequestBiometricData biometricdata;

    @OneToOne
    @JoinColumn(unique = true)
    private LicenseCategory licenseCategory;

    @OneToOne
    @JoinColumn(unique = true)
    private TransactionType transacionType;

    @OneToOne
    @JoinColumn(unique = true)
    private TrafficUnits trafficUnit;

    @OneToOne
    @JoinColumn(unique = true)
    private RequestStatus status;

    @OneToMany(mappedBy = "medicalInsepctionRequests")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RequestInseptionDetails> medicalInsepctionRequests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public MedicalInsepctionRequests requestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
        return this;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Persons getPerson() {
        return person;
    }

    public MedicalInsepctionRequests person(Persons persons) {
        this.person = persons;
        return this;
    }

    public void setPerson(Persons persons) {
        this.person = persons;
    }

    public RequestBiometricData getBiometricdata() {
        return biometricdata;
    }

    public MedicalInsepctionRequests biometricdata(RequestBiometricData requestBiometricData) {
        this.biometricdata = requestBiometricData;
        return this;
    }

    public void setBiometricdata(RequestBiometricData requestBiometricData) {
        this.biometricdata = requestBiometricData;
    }

    public LicenseCategory getLicenseCategory() {
        return licenseCategory;
    }

    public MedicalInsepctionRequests licenseCategory(LicenseCategory licenseCategory) {
        this.licenseCategory = licenseCategory;
        return this;
    }

    public void setLicenseCategory(LicenseCategory licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public TransactionType getTransacionType() {
        return transacionType;
    }

    public MedicalInsepctionRequests transacionType(TransactionType transactionType) {
        this.transacionType = transactionType;
        return this;
    }

    public void setTransacionType(TransactionType transactionType) {
        this.transacionType = transactionType;
    }

    public TrafficUnits getTrafficUnit() {
        return trafficUnit;
    }

    public MedicalInsepctionRequests trafficUnit(TrafficUnits trafficUnits) {
        this.trafficUnit = trafficUnits;
        return this;
    }

    public void setTrafficUnit(TrafficUnits trafficUnits) {
        this.trafficUnit = trafficUnits;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public MedicalInsepctionRequests status(RequestStatus requestStatus) {
        this.status = requestStatus;
        return this;
    }

    public void setStatus(RequestStatus requestStatus) {
        this.status = requestStatus;
    }

    public Set<RequestInseptionDetails> getMedicalInsepctionRequests() {
        return medicalInsepctionRequests;
    }

    public MedicalInsepctionRequests medicalInsepctionRequests(Set<RequestInseptionDetails> requestInseptionDetails) {
        this.medicalInsepctionRequests = requestInseptionDetails;
        return this;
    }

    public MedicalInsepctionRequests addMedicalInsepctionRequests(RequestInseptionDetails requestInseptionDetails) {
        this.medicalInsepctionRequests.add(requestInseptionDetails);
        requestInseptionDetails.setMedicalInsepctionRequests(this);
        return this;
    }

    public MedicalInsepctionRequests removeMedicalInsepctionRequests(RequestInseptionDetails requestInseptionDetails) {
        this.medicalInsepctionRequests.remove(requestInseptionDetails);
        requestInseptionDetails.setMedicalInsepctionRequests(null);
        return this;
    }

    public void setMedicalInsepctionRequests(Set<RequestInseptionDetails> requestInseptionDetails) {
        this.medicalInsepctionRequests = requestInseptionDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInsepctionRequests)) {
            return false;
        }
        return id != null && id.equals(((MedicalInsepctionRequests) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInsepctionRequests{" +
            "id=" + getId() +
            ", requestNumber='" + getRequestNumber() + "'" +
            "}";
    }
}
