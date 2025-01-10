package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VisitDaoTest {

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private PatientDao patientDao;


    @Transactional
    @Test
    public void testGetVisitsByPatientId() {
        PatientEntity patientEntity = patientDao.getOne(1L);

        List<VisitEntity> visits = visitDao.getVisitsByPatientId(patientEntity.getId());

        assertThat(visits).isNotEmpty();

        for (VisitEntity visit : visits) {
            assertThat(visit.getPatient().getId()).isEqualTo(patientEntity.getId());
        }

    }

    @Transactional
    @Test
    public void testGetVisitsByNonExistentPatientId() {
        // Pacjent o id 2 nie ma wizyt
        List<VisitEntity> visits = visitDao.getVisitsByPatientId(2L);

        assertThat(visits).isEmpty();
    }
}
