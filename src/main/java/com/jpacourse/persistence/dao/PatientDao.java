package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    List<PatientEntity> getPatientsByLastName(String lastName);

    List<PatientEntity> getPatientsWithMoreThanXVisits(int x);

    List<PatientEntity> getPatientsByAdditionalInfo(String additionalInfo);
}
