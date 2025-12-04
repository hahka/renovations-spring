package com.example.renovations.projects;

import java.net.URI;
import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/projects")
    public Page<ProjectDto> getProjects(@RequestParam String search, Pageable p, HttpServletRequest request) {
        return projectsService.getProjects(request, search, p);
    }

    @PostAuthorize("@projectsPermissions.ownsProject(#request, returnObject)")
    @GetMapping("/projects/{projectId}")
    public ProjectDto getProjectById(HttpServletRequest request, @PathVariable String projectId) {
        return projectsService.getProjectById(request, projectId);
    }

    @PostMapping("/projects")
    ResponseEntity<Object> addProject(HttpServletRequest request, @RequestBody Project project, HttpServletResponse response) {
        var createdProject = projectsService.createProject(request, project);
        URI location = ServletUriComponentsBuilder.fromPath(request.getHeader("referer")).path("/projects/{id}").buildAndExpand(createdProject.getId()).toUri();
        return ResponseEntity.created(location).body(createdProject);
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
