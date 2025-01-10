package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class VisitMapper {

    public static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDoctorName(visitEntity.getDoctor() != null ? visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName() : null);
        visitTO.setVisitDate(visitEntity.getVisitDate());
        visitTO.setDescription(visitEntity.getDescription());

        if (visitEntity.getMedicalTreatments() != null) {
            visitTO.setTreatments(visitEntity.getMedicalTreatments()
                    .stream()
                    .map(MedicalTreatmentMapper::mapToTO)
                    .collect(Collectors.toList()));
        }

        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();

        visitEntity.setVisitDate(visitTO.getVisitDate());
        visitEntity.setDescription(visitTO.getDescription());


        return visitEntity;
    }
}
