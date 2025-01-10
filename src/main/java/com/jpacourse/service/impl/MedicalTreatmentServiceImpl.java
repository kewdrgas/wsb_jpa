package com.jpacourse.service.impl;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.mapper.MedicalTreatmentMapper;
import com.jpacourse.persistence.dao.MedicalTreatmentDao;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.service.MedicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalTreatmentServiceImpl implements MedicalTreatmentService {

    private final MedicalTreatmentDao medicalTreatmentDao;

    @Autowired
    public MedicalTreatmentServiceImpl(MedicalTreatmentDao medicalTreatmentDao) {
        this.medicalTreatmentDao = medicalTreatmentDao;
    }

    @Override
    public MedicalTreatmentTO findById(Long id) {
        final MedicalTreatmentEntity entity = medicalTreatmentDao.findOne(id);
        return MedicalTreatmentMapper.mapToTO(entity);
    }

    @Override
    public MedicalTreatmentTO save(MedicalTreatmentTO medicalTreatmentTO) {
        MedicalTreatmentEntity entity = MedicalTreatmentMapper.mapToEntity(medicalTreatmentTO);
        entity = medicalTreatmentDao.save(entity);
        return MedicalTreatmentMapper.mapToTO(entity);
    }

    @Override
    public void delete(Long id) {
        medicalTreatmentDao.delete(id);
    }
}
