package com.jpacourse.service.impl;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.mapper.DoctorMapper;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public DoctorTO findById(Long id) {
        final DoctorEntity entity = doctorDao.findOne(id);
        return DoctorMapper.mapToTO(entity);
    }

    @Override
    public DoctorTO save(DoctorTO doctorTO) {
        DoctorEntity entity = DoctorMapper.mapToEntity(doctorTO);
        entity = doctorDao.save(entity);
        return DoctorMapper.mapToTO(entity);
    }

    @Override
    public void delete(Long id) {
        doctorDao.delete(id);
    }
}
