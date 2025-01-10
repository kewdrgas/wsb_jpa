package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id); // Using findOne from Dao
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public PatientTO save(PatientTO patientTO) {
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        entity = patientDao.save(entity); // Using save from Dao
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void delete(Long id) {
        patientDao.delete(id);
    }
}
