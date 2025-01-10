package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setPatientId(visitEntity.getPatient().getId());
        visitTO.setDoctorId(visitEntity.getDoctor().getId());
        visitTO.setVisitDate(visitEntity.getVisitDate());
        visitTO.setDescription(visitEntity.getDescription());
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(new PatientEntity());  // Set the actual PatientEntity here
        visitEntity.setDoctor(new DoctorEntity());    // Set the actual DoctorEntity here
        visitEntity.setVisitDate(visitTO.getVisitDate());
        visitEntity.setDescription(visitTO.getDescription());
        return visitEntity;
    }
}
