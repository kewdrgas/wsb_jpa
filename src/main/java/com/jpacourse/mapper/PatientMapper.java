package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientTO mapToTO(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        patientTO.setAdditionalInfo(patientEntity.getAdditionalInfo());

        if (patientEntity.getVisits() != null) {
            patientTO.setVisits(patientEntity.getVisits()
                    .stream()
                    .map(VisitMapper::mapToTO)
                    .collect(Collectors.toList()));
        }

        return patientTO;
    }

    public static PatientEntity mapToEntity(PatientTO patientTO) {
        if (patientTO == null) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setAdditionalInfo(patientTO.getAdditionalInfo());

        if (patientTO.getAddress() != null) {
            patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        }

        return patientEntity;
    }
}
