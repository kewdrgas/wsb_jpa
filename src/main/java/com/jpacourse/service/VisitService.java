package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface VisitService {
    VisitTO findById(Long id);
    VisitTO save(VisitTO visitTO);
    void delete(Long id);

    List<VisitTO> findVisitsByPatientId(Long patientId);
}
