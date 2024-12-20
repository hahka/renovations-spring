package com.example.renovations.works;

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
public class WorkController {
    private final WorksRepository workRepository;
    private final WorksService worksService;

    WorkController(WorksRepository workRepository, WorksService worksService) {
      this.workRepository = workRepository;
      this.worksService = worksService;
    }

    @GetMapping("/works")
    public List<WorkDto> getWorks(HttpServletRequest request) {
        return worksService.getWorks(request);
    }

    @PostAuthorize("@worksPermissions.ownsWork(#request, returnObject)")
    @GetMapping("/works/{workId}")
    public WorkDto getWorkById(HttpServletRequest request, @PathVariable String workId) {
        return worksService.getWorkById(request, workId);
    }

    @PostMapping("/works")
    void addWork(@RequestBody Work work) {
        workRepository.save(work);
    }

    @PatchMapping("/works/{workId}")
    ResponseEntity<Object> patchProject(HttpServletRequest request, @PathVariable String workId, @RequestBody WorkDto workDto) throws AccessDeniedException {
        try {
            worksService.patchWork(request, workId, workDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}