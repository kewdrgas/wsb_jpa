-- Insert Data into Address Table
INSERT INTO ADDRESS (id, address_line1, address_line2, city, postal_code)
VALUES
    (1, 'ul. Szkolna 21/12', NULL, 'Prudnik', '48-200'),
    (2, 'ul. Dworcowa 25', 'lok. 23', 'Gliwice', '44-100'),
    (3, 'ul. Mickiewicza 22', NULL, 'Kraków', '30-001'),
    (4, 'ul. Wojska Polskiego 28/3', NULL, 'Warszawa', '00-001'),
    (5, 'ul. Lipowa 29/4', NULL, 'Gdańsk', '80-001'),
    (6, 'ul. Leśna 20A', NULL, 'Katowice', '40-200'),
    (7, 'ul. Krótka 212', 'lok. 45', 'Poznań', '60-101'),
    (8, 'ul. Długa 23', NULL, 'Wrocław', '50-200'),
    (9, 'ul. Chopina 24/6', NULL, 'Sopot', '81-701'),
    (10, 'ul. Wrzosowa 25', NULL, 'Łódź', '90-100'),
    (11, 'ul. Opolska 36', NULL, 'Opole', '45-011'),
    (12, 'ul. Przyjaźni 37', 'lok. 89', 'Bydgoszcz', '85-019'),
    (13, 'ul. Nadrzeczna 38', NULL, 'Lublin', '20-001'),
    (14, 'ul. Główna 39', 'lok. 134', 'Rzeszów', '35-001'),
    (15, 'ul. Szewska 40', NULL, 'Białystok', '15-011'),
    (16, 'ul. Kwiatowa 41', 'lok. 12B', 'Zielona Góra', '65-200'),
    (17, 'ul. Zamkowa 42', 'lok. 45C', 'Toruń', '87-100'),
    (18, 'ul. Śląska 43', NULL, 'Częstochowa', '42-200'),
    (19, 'ul. Racławicka 44', NULL, 'Racibórz', '47-400'),
    (20, 'ul. Wodna 45', NULL, 'Włocławek', '87-800'),
    (21, 'ul. Tęczowa 22', 'lok. 23', 'Gliwice', '44-100'),
    (22, 'ul. Wiosenna 23', NULL, 'Kraków', '30-001'),
    (23, 'ul. Jesienna 24', NULL, 'Warszawa', '00-001'),
    (24, 'ul. Letnia 25', NULL, 'Gdańsk', '80-001'),
    (25, 'ul. Zimowa 20A', NULL, 'Katowice', '40-200'),
    (26, 'ul. Letnia 212', 'lok. 45', 'Poznań', '60-101'),
    (27, 'ul. Zimowa 23', NULL, 'Wrocław', '50-200'),
    (28, 'ul. Letnia 24/6', NULL, 'Sopot', '81-701'),
    (29, 'ul. Zimowa 25', NULL, 'Łódź', '90-100'),
    (30, 'ul. Letnia 36', NULL, 'Opole', '45-011'),
    (31, 'ul. Zimowa 37', 'lok. 89', 'Bydgoszcz', '85-019'),
    (32, 'ul. Letnia 38', NULL, 'Lublin', '20-001'),
    (33, 'ul. Zimowa 39', 'lok. 134', 'Rzeszów', '35-001'),
    (34, 'ul. Letnia 40', NULL, 'Białystok', '15-011'),
    (35, 'ul. Zimowa 41', 'lok. 12B', 'Zielona Góra', '65-200'),
    (36, 'ul. Letnia 42', 'lok. 45C', 'Toruń', '87-100'),
    (37, 'ul. Zimowa 43', NULL, 'Częstochowa', '42-200'),
    (38, 'ul. Letnia 44', NULL, 'Racibórz', '47-400'),
    (39, 'ul. Zimowa 45', NULL, 'Włocławek', '87-800');

INSERT INTO doctor (first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
    ('John', 'Doe', '123456789', 'johndoe@example.com', 'D123', 'SURGEON', 2),
    ('Jane', 'Smith', '987654321', 'janesmith@example.com', 'D124', 'SURGEON', 3),
    ('Emma', 'Taylor', '321654987', 'emmataylor@example.com', 'D125', 'GP', 4),
    ('Robert', 'Brown', '654987321', 'robertbrown@example.com', 'D126', 'DERMATOLOGIST', 5),
    ('Michael', 'Johnson', '789456123', 'michaeljohnson@example.com', 'D127', 'OCULIST', 6),
    ('Lucas', 'Davis', '987321654', 'lucasdavis@example.com', 'D128', 'SURGEON', 7),
    ('Sophia', 'Martinez', '123987654', 'sophiamartinez@example.com', 'D129', 'GP', 8),
    ('David', 'Garcia', '741258963', 'davidgarcia@example.com', 'D130', 'OCULIST', 9),
    ('Olivia', 'Miller', '852369741', 'oliviamiller@example.com', 'D131', 'SURGEON', 10),
    ('Daniel', 'Wilson', '963741852', 'danielwilson@example.com', 'D132', 'DERMATOLOGIST', 11);

INSERT INTO patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, additional_info)
VALUES
    ('Alicja', 'Kowalska', '555-1234', 'alicjakowalska@example.com', 'P001', '1990-01-15', 1, 'Testowe dodatkowe informacje'),
    ('Bartosz', 'Wiśniewski', '555-5678', 'bartoszwisniewski@example.com', 'P002', '1985-06-20', 4, 'Pacjent bez wizyt'),
    ('Cezary', 'Nowak', '555-8765', 'cezarynowak@example.com', 'P003', '1982-11-30', 7, 'Dodatkowe informacje pacjenta C'),
    ('Diana', 'Kowalczyk', '555-3456', 'dianakowalczyk@example.com', 'P004', '1995-05-17', 10, 'Pacjent ma alergie na penicylinę'),
    ('Eryk', 'Wójcik', '555-6543', 'erykwojcik@example.com', 'P005', '2000-02-22', 13, 'Brak dodatkowych informacji');


INSERT INTO visit (doctor_id, patient_id, visit_date, description)
VALUES
    (1, 1, '2025-01-10 09:00:00', 'Rutynowa kontrola'),
    (2, 1, '2025-01-11 10:00:00', 'Konsultacja dermatologiczna'),
    (3, 3, '2025-01-12 11:00:00', 'Konsultacja ogólna'),
    (4, 3, '2025-01-13 12:00:00', 'Badanie skóry'),
    (5, 4, '2025-01-14 13:00:00', 'Badanie oczu');

INSERT INTO medical_treatment (visit_id, description, type)
VALUES
    (1, 'Monitorowanie ciśnienia krwi', 'USG'),
    (2, 'Badanie skóry', 'USG'),
    (3, 'Ogólne badanie kontrolne', 'EKG'),
    (4, 'Leczenie dermatologiczne', 'RTG'),
    (5, 'Leczenie oczu', 'EKG');

