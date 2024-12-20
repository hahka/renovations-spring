package com.example.renovations.worktypes;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
    private final WorkTypesRepository workTypesRepository;
    private final WorkTypesService workTypesService;

    WorkTypesController(WorkTypesRepository workTypesRepository, WorkTypesService workTypesService) {
      this.workTypesRepository = workTypesRepository;
      this.workTypesService = workTypesService;
    }

    @GetMapping("/work_types")
    public List<WorkTypeDto> getWorkTypes(HttpServletRequest request) {
        return workTypesService.getWorkTypes(request);
    }

    @GetMapping("/work_types/{workTypeId}")
    public WorkTypeDto getWorkById(HttpServletRequest request, @PathVariable String workTypeId) {
        return workTypesService.getWorkTypeById(request, workTypeId);
    }

    @PostMapping("/work_types")
    void addWork(@RequestBody WorkType workType) {
        workTypesRepository.save(workType);
    }

    @PatchMapping("/work_types/{workId}")
    ResponseEntity<Object> patchProject(HttpServletRequest request, @PathVariable String workTypeId, @RequestBody WorkTypeDto workTypeDto) throws AccessDeniedException {
        try {
            workTypesService.patchWorkType(request, workTypeId, workTypeDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            System.out.println("FORBIDDEN");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}