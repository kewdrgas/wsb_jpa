package com.jpacourse.rest;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.MedicalTreatmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-treatment")
public class MedicalTreatmentController {

    private final MedicalTreatmentService medicalTreatmentService;

    public MedicalTreatmentController(MedicalTreatmentService medicalTreatmentService) {
        this.medicalTreatmentService = medicalTreatmentService;
    }

    @GetMapping("/{id}")
    public MedicalTreatmentTO findById(@PathVariable final Long id) {
        final MedicalTreatmentTO medicalTreatment = medicalTreatmentService.findById(id);
        if (medicalTreatment != null) {
            return medicalTreatment;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping
    public MedicalTreatmentTO save(@RequestBody MedicalTreatmentTO medicalTreatmentTO) {
        return medicalTreatmentService.save(medicalTreatmentTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        medicalTreatmentService.delete(id);
    }
}
