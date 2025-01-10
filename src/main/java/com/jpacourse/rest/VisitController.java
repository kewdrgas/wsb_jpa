package com.jpacourse.rest;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.VisitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    public VisitTO findById(@PathVariable final Long id) {
        final VisitTO visit = visitService.findById(id);
        if (visit != null) {
            return visit;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping
    public VisitTO save(@RequestBody VisitTO visitTO) {
        return visitService.save(visitTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        visitService.delete(id);
    }
}
