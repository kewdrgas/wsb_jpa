package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public PatientTO findById(@PathVariable final Long id) {
        final PatientTO patient = patientService.findById(id);
        if (patient != null) {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping
    public PatientTO save(@RequestBody final PatientTO patient) {
        return patientService.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        patientService.delete(id);
    }
}
