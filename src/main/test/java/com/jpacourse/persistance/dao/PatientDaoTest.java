package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void testAddVisitToPatient() {
        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("ul. Szkolna 21/12");
        patientAddress.setCity("Prudnik");
        patientAddress.setPostalCode("48-200");

        AddressEntity doctorAddress = new AddressEntity();
        doctorAddress.setAddressLine1("ul. Dworcowa 25");
        doctorAddress.setCity("Gliwice");
        doctorAddress.setPostalCode("44-100");

        patientAddress = addressDao.save(patientAddress);
        doctorAddress = addressDao.save(doctorAddress);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Alicja");
        patientEntity.setLastName("Kowalska");
        patientEntity.setDateOfBirth(LocalDate.of(1999, 2, 2));
        patientEntity.setTelephoneNumber("555-1234");
        patientEntity.setEmail("alice@example.com");
        patientEntity.setPatientNumber("P001");
        patientEntity.setAddress(patientAddress);
        patientEntity.setAdditionalInfo("Testowe dodatkowe informacje");

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName("Jan");
        doctorEntity.setLastName("Nowak");
        doctorEntity.setTelephoneNumber("123456789");
        doctorEntity.setEmail("johndoe@example.com");
        doctorEntity.setDoctorNumber("D123");
        doctorEntity.setSpecialization(Specialization.valueOf("SURGEON"));
        doctorEntity.setAddress(doctorAddress);

        doctorEntity = doctorDao.save(doctorEntity);
        patientEntity = patientDao.save(patientEntity);

        LocalDateTime visitDate = LocalDateTime.of(2025, 1, 15, 10, 0);
        String visitDescription = "Wizyta kontrolna";


        patientDao.addVisitToPatient(patientEntity.getId(), doctorEntity.getId(), visitDate, visitDescription);

        PatientEntity patient = patientDao.getOne(patientEntity.getId());
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotEmpty();

        VisitEntity addedVisit = patient.getVisits().stream()
                .filter(v -> v.getDescription().equals(visitDescription))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Visit not added"));

        assertThat(addedVisit.getDescription()).isEqualTo(visitDescription);
        assertThat(addedVisit.getVisitDate()).isEqualTo(visitDate);
        assertThat(addedVisit.getDoctor().getFirstName()).isEqualTo(doctorEntity.getFirstName());
        assertThat(addedVisit.getPatient().getFirstName()).isEqualTo(patientEntity.getFirstName());
    }

    @Transactional
    @Test
    public void testGetPatientsByLastName() {
        List<PatientEntity> patients = patientDao.getPatientsByLastName("Kowalska");
        assertThat(patients).isNotEmpty();
        patients.forEach(patient -> assertThat(patient.getLastName()).isEqualTo("Kowalska"));
    }

    @Transactional
    @Test
    public void testGetPatientsWithMoreThanXVisits() {
        // Arrange: Create patients with varying number of visits
        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("ul. Długa 22");
        patientAddress.setCity("Warszawa");
        patientAddress.setPostalCode("00-100");

        patientAddress = addressDao.save(patientAddress);

        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("Marek");
        patient1.setLastName("Zieliński");
        patient1.setDateOfBirth(LocalDate.of(1980, 7, 12));
        patient1.setTelephoneNumber("555-1111");
        patient1.setEmail("marek.zielinski@example.com");
        patient1.setPatientNumber("P006");
        patient1.setAddress(patientAddress);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Katarzyna");
        patient2.setLastName("Nowak");
        patient2.setDateOfBirth(LocalDate.of(1990, 3, 5));
        patient2.setTelephoneNumber("555-2222");
        patient2.setEmail("katarzyna.nowak@example.com");
        patient2.setPatientNumber("P007");
        patient2.setAddress(patientAddress);

        patient1 = patientDao.save(patient1);
        patient2 = patientDao.save(patient2);

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName("Tomasz");
        doctorEntity.setLastName("Kwiatkowski");
        doctorEntity.setTelephoneNumber("123456789");
        doctorEntity.setEmail("tomasz.kwiatkowski@example.com");
        doctorEntity.setDoctorNumber("D124");
        doctorEntity.setSpecialization(Specialization.valueOf("GP"));
        doctorEntity.setAddress(patientAddress);

        doctorEntity = doctorDao.save(doctorEntity);

        LocalDateTime visitDate = LocalDateTime.of(2025, 1, 15, 10, 0);
        String visitDescription = "Wizyta kontrolna";

        for (int i = 0; i < 3; i++) {
            patientDao.addVisitToPatient(patient1.getId(), doctorEntity.getId(), visitDate.plusDays(i), visitDescription);
        }

        patientDao.addVisitToPatient(patient2.getId(), doctorEntity.getId(), visitDate, visitDescription);

        List<PatientEntity> patientsWithMoreThan2Visits = patientDao.getPatientsWithMoreThanXVisits(2);


        assertThat(patientsWithMoreThan2Visits).hasSize(1);
        assertThat(patientsWithMoreThan2Visits.get(0).getFirstName()).isEqualTo("Marek");
    }

    @Transactional
    @Test
    public void testGetPatientsByAdditionalInfo() {
        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("ul. Pięciomorgowa 100");
        patientAddress.setCity("Kraków");
        patientAddress.setPostalCode("30-100");

        patientAddress = addressDao.save(patientAddress);

        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("Łukasz");
        patient1.setLastName("Bąk");
        patient1.setDateOfBirth(LocalDate.of(1985, 4, 15));
        patient1.setTelephoneNumber("555-3333");
        patient1.setEmail("lukasz.bak@example.com");
        patient1.setPatientNumber("P008");
        patient1.setAddress(patientAddress);
        patient1.setAdditionalInfo("Pacjent z alergią na pyłki");

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Anna");
        patient2.setLastName("Kwiatkowska");
        patient2.setDateOfBirth(LocalDate.of(1992, 8, 25));
        patient2.setTelephoneNumber("555-4444");
        patient2.setEmail("anna.kwiatkowska@example.com");
        patient2.setPatientNumber("P009");
        patient2.setAddress(patientAddress);
        patient2.setAdditionalInfo("Pacjent bez alergii");

        patient1 = patientDao.save(patient1);
        patient2 = patientDao.save(patient2);

        List<PatientEntity> patientsWithAllergyInfo = patientDao.getPatientsByAdditionalInfo("Pacjent z alergią na pyłki");

        assertThat(patientsWithAllergyInfo).hasSize(1);
        assertThat(patientsWithAllergyInfo.get(0).getFirstName()).isEqualTo("Łukasz");
        assertThat(patientsWithAllergyInfo.get(0).getAdditionalInfo()).isEqualTo("Pacjent z alergią na pyłki");
    }
}
