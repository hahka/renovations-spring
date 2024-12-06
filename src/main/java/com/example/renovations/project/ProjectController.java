package com.example.renovations.project;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    ProjectController(ProjectRepository projectRepository, ProjectMapper projectMapper) {
      this.projectRepository = projectRepository;
      this.projectMapper = projectMapper;
    }

    @GetMapping("/projects")
    public List<ProjectDto> getProjects() {
        List<Project> projects = (List<Project>) projectRepository.findAll();
        return projects.stream().map(project -> projectMapper.toDto(project)).collect(Collectors.toList()); 
    }

    @PostMapping("/projects")
    void addProject(@RequestBody Project project) {
        projectRepository.save(project);
    }
}
