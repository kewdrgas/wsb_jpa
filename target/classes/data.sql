-- 1. Inserting data into the Address table (addresses must be inserted first because both Doctor and Patient depend on it)
insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'xx', 'yy', 'city', '62-030');

-- 2. Inserting data into the Doctor table (Doctors must be inserted after Address)
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values (1, 'John', 'Doe', '123456789', 'johndoe@example.com', 'D123', 'CARDIOLOGY', 1),
       (2, 'Jane', 'Smith', '987654321', 'janesmith@example.com', 'D124', 'DERMATOLOGY', 1);

-- 3. Inserting data into the Patient table (Patients must be inserted after Address)
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
values (1, 'Alice', 'Johnson', '555-1234', 'alicejohnson@example.com', 'P001', '1990-01-15', 1),
       (2, 'Bob', 'Williams', '555-5678', 'bobwilliams@example.com', 'P002', '1985-06-20', 1);

-- 4. Inserting data into the Visit table (Visits must be inserted after Doctor and Patient)
insert into visit (id, doctor_id, patient_id, visit_date, description)
values (1, 1, 1, '2025-01-10 09:00:00', 'Routine check-up'),
       (2, 2, 2, '2025-01-11 10:00:00', 'Dermatology consultation');

-- 5. Inserting data into the MedicalTreatment table (Treatments must be inserted after Visit)
insert into medical_treatment (id, visit_id, description, type)
values (1, 1, 'Blood pressure monitoring', 'TREATMENT'),
       (2, 2, 'Skin examination', 'CONSULTATION');
