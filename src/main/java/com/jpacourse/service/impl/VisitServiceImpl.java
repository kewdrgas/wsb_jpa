package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitDao visitDao;

    @Autowired
    public VisitServiceImpl(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public VisitTO findById(Long id) {
        final VisitEntity entity = visitDao.findOne(id);
        return VisitMapper.mapToTO(entity);
    }

    @Override
    public VisitTO save(VisitTO visitTO) {
        VisitEntity entity = VisitMapper.mapToEntity(visitTO);
        entity = visitDao.save(entity);
        return VisitMapper.mapToTO(entity);
    }

    @Override
    public void delete(Long id) {
        visitDao.delete(id);
    }

    @Override
    public List<VisitTO> findVisitsByPatientId(Long patientId) {
        List<VisitEntity> visits = visitDao.getVisitsByPatientId(patientId);
        return visits.stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
