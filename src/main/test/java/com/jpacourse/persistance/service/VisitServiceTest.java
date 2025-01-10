package com.jpacourse.persistance.service;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testFindVisitsByPatientId() {
        PatientEntity patientEntity = patientDao.findOne(1L);
        assertThat(patientEntity).isNotNull();

        List<VisitTO> visits = visitService.findVisitsByPatientId(patientEntity.getId());

        assertThat(visits).isNotEmpty();
        assertThat(visits.size()).isGreaterThan(0);

        assertThat(visits.get(0).getDescription()).isEqualTo("Rutynowa kontrola");
        assertThat(visits.get(1).getDescription()).isEqualTo("Konsultacja dermatologiczna");
    }

    @Transactional
    @Test
    public void testFindVisitsByPatientIdWithNoVisits() {
        PatientEntity patientEntity = patientDao.findOne(2L);
        assertThat(patientEntity).isNotNull();

        List<VisitTO> visits = visitService.findVisitsByPatientId(patientEntity.getId());

        assertThat(visits).isEmpty();
    }
}
