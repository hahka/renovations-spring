package com.example.renovations.worktypes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkTypesController {
    private final WorkTypesService workTypesService;

    WorkTypesController(WorkTypesService workTypesService) {
        this.workTypesService = workTypesService;
    }

    @GetMapping("/work_types")
    public List<WorkTypeDto> getWorkTypes(HttpServletRequest request) {
        return workTypesService.getWorkTypes(request);
    }

    @GetMapping("/work_types/{workTypeId}")
    public ResponseEntity<WorkTypeDto> getWorkTypeById(HttpServletRequest request, @PathVariable String workTypeId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(workTypesService.getWorkTypeById(request, workTypeId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping("/work_types")
    void postWorkType(HttpServletRequest request, @RequestBody WorkType workType) {
        workTypesService.postWorkType(request, workType);
    }

    @PatchMapping("/work_types/{workTypeId}")
    ResponseEntity<Object> patchWorkType(HttpServletRequest request, @PathVariable String workTypeId,
            @RequestBody WorkTypeDto workTypeDto) {
        try {
            workTypesService.patchWorkType(request, workTypeId, workTypeDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}