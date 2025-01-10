package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientService {

    PatientTO findById(final Long id);

    PatientTO save(final PatientTO patient);

    void delete(final Long id);

    void addVisitToPatient(Long patientId, Long visitId);

    List<PatientTO> findPatientsWithMoreThanXVisits(int x);

    List<PatientTO> findPatientsByAdditionalInfo(String additionalInfo);
}
