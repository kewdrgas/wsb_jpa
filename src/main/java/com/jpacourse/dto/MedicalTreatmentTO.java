package com.jpacourse.dto;

import com.jpacourse.persistence.enums.TreatmentType;

import java.io.Serializable;

public class MedicalTreatmentTO implements Serializable {

    private Long id;
    private Long visitId;  // Reference to the associated visit
    private String description;  // Description of the medical treatment
    private TreatmentType type;  // Type of treatment (e.g., Surgery, Medication)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }
}
