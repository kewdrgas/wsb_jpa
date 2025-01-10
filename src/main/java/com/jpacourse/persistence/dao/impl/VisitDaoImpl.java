package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {
    @Transactional
    public List<VisitEntity> getVisitsByPatientId(Long patientId) {
        String jpql = "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId";
        TypedQuery<VisitEntity> query = entityManager.createQuery(jpql, VisitEntity.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }
}
