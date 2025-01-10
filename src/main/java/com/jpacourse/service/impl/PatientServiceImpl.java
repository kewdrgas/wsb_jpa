package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;
    private final VisitDao visitDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, VisitDao visitDao) {
        this.patientDao = patientDao;
        this.visitDao = visitDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public PatientTO save(PatientTO patientTO) {
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        entity = patientDao.save(entity);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void delete(Long id) {
        patientDao.delete(id);
    }

    @Transactional
    @Override
    public void addVisitToPatient(Long patientId, Long visitId) {
        PatientEntity patient = patientDao.findOne(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found with ID: " + patientId);
        }

        VisitEntity visit = visitDao.findOne(visitId);
        if (visit == null) {
            throw new IllegalArgumentException("Visit not found with ID: " + visitId);
        }

        visit.setPatient(patient);
        visitDao.save(visit);

        patient.getVisits().add(visit);
        patientDao.save(patient);
    }

    @Override
    public List<PatientTO> findPatientsWithMoreThanXVisits(int x) {
        List<PatientEntity> patients = patientDao.getPatientsWithMoreThanXVisits(x);

        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findPatientsByAdditionalInfo(String additionalInfo) {
        List<PatientEntity> patients = patientDao.getPatientsByAdditionalInfo(additionalInfo);

        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
