package com.example.renovations.projects;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectsController {
    private final ProjectsRepository projectRepository;
    private final ProjectsService projectsService;

    ProjectsController(ProjectsService projectsService, ProjectsRepository projectsRepository) {
      this.projectsService = projectsService;
      this.projectRepository = projectsRepository;
    }

    @GetMapping("/projects")
    public List<ProjectDto> getProjects(HttpServletRequest request) {
        return projectsService.getProjects(request);
    }

    @PostMapping("/projects")
    void addProject(@RequestBody Project project) {
        projectRepository.save(project);
    }
}
