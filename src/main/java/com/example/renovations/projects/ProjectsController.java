package com.example.renovations.projects;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/projects")
    public Page<ProjectDto> getProjects(Pageable p, HttpServletRequest request) {
        return projectsService.getProjects(request, p);
    }

    @PostAuthorize("@projectsPermissions.ownsProject(#request, returnObject)")
    @GetMapping("/projects/{projectId}")
    public ProjectDto getProjectById(HttpServletRequest request, @PathVariable String projectId) {
        return projectsService.getProjectById(request, projectId);
    }

    @PostMapping("/projects")
    void addProject(HttpServletRequest request, @RequestBody Project project) {
        projectsService.createProject(request, project);
    }

    @PatchMapping("/projects/{projectId}")
    ResponseEntity<Object> patchProject(HttpServletRequest request, @PathVariable String projectId, @RequestBody ProjectDto projectDto) throws AccessDeniedException {
        try {
            projectsService.patchProject(request, projectId, projectDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            System.out.println("FORBIDDEN");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
