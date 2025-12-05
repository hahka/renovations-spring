package com.example.renovations.works;

import java.nio.file.AccessDeniedException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WorksController {
    private final WorksRepository workRepository;
    private final WorksService worksService;

    WorksController(WorksRepository workRepository, WorksService worksService) {
      this.workRepository = workRepository;
      this.worksService = worksService;
    }

    @GetMapping("/works")
    public Page<WorkDto> getWorks(@RequestParam String search, Pageable p, HttpServletRequest request) {
        return worksService.getWorks(request, search, p);
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