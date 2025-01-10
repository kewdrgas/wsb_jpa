package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = getOne(patientId);
        DoctorEntity doctor = doctorDao.getOne(doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setVisitDate(visitDate);
        visit.setDescription(description);
        visit.setPatient(patient);
        visit.setDoctor(doctor);

        patient.getVisits().add(visit);

        save(patient);
    }

    @Override
    public List<PatientEntity> getPatientsByLastName(String lastName) {
        String jpql = "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName";
        TypedQuery<PatientEntity> query = entityManager.createQuery(jpql, PatientEntity.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<PatientEntity> getPatientsWithMoreThanXVisits(int x) {
        String jpql = "SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :visitCount";
        TypedQuery<PatientEntity> query = entityManager.createQuery(jpql, PatientEntity.class);
        query.setParameter("visitCount", x);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<PatientEntity> getPatientsByAdditionalInfo(String additionalInfo) {
        String jpql = "SELECT p FROM PatientEntity p WHERE LOWER(p.additionalInfo) LIKE LOWER(:additionalInfo)";
        TypedQuery<PatientEntity> query = entityManager.createQuery(jpql, PatientEntity.class);
        query.setParameter("additionalInfo", "%" + additionalInfo + "%");
        return query.getResultList();
    }
}
