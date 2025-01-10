package com.jpacourse.persistance.service;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldDeletePatientAndCascadeVisits() {
        PatientEntity patientEntity = patientDao.findOne(1L);
        assertThat(patientEntity).isNotNull();

        VisitEntity visitEntity = visitDao.findOne(1L);
        assertThat(visitEntity).isNotNull();

        DoctorEntity doctorEntity = doctorDao.findOne(1L);
        assertThat(doctorEntity).isNotNull();

        patientService.delete(patientEntity.getId());

        PatientEntity deletedPatient = patientDao.findOne(patientEntity.getId());
        assertThat(deletedPatient).isNull();

        VisitEntity deletedVisit = visitDao.findOne(visitEntity.getId());
        assertThat(deletedVisit).isNull();

        DoctorEntity doctorEntityAfterDelete = doctorDao.findOne(doctorEntity.getId());
        assertThat(doctorEntityAfterDelete).isNotNull();
        assertThat(doctorEntityAfterDelete.getFirstName()).isEqualTo("John");
    }

    @Transactional
    @Test
    public void testShouldFindPatientByIdWithVisits() {
        PatientEntity patientEntity = patientDao.findOne(1L);
        assertThat(patientEntity).isNotNull();

        VisitEntity visitEntity = visitDao.findOne(1L);
        assertThat(visitEntity).isNotNull();

        PatientTO patientTO = patientService.findById(patientEntity.getId());

        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getVisits()).isNotEmpty();
        assertThat(patientTO.getVisits().get(0).getDescription()).isEqualTo("Rutynowa kontrola");
        assertThat(patientTO.getAdditionalInfo()).isEqualTo("Testowe dodatkowe informacje");
    }

    @Transactional
    @Test
    public void testShouldFindPatientByIdWithNoVisits() {
        PatientEntity patientEntity = patientDao.findOne(2L);
        assertThat(patientEntity).isNotNull();

        PatientTO patientTO = patientService.findById(patientEntity.getId());

        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getVisits()).isEmpty();
        assertThat(patientTO.getAdditionalInfo()).isEqualTo("Pacjent bez wizyt");
    }
}
