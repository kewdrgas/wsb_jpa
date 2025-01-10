package com.jpacourse.rest;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public DoctorTO findById(@PathVariable final Long id) {
        final DoctorTO doctor = doctorService.findById(id);
        if (doctor != null) {
            return doctor;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping
    public DoctorTO save(@RequestBody DoctorTO doctorTO) {
        return doctorService.save(doctorTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        doctorService.delete(id);
    }
}
