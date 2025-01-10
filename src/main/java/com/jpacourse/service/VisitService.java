package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

public interface VisitService {
    VisitTO findById(Long id);
    VisitTO save(VisitTO visitTO);
    void delete(Long id);
}
