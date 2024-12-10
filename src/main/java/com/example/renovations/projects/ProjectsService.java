package com.example.renovations.projects;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProjectsService {
    ProjectsRepository projectsRepository;

    ProjectMapper projectMapper;
    
    TokenProvider tokenService;

    ProjectsService(ProjectsRepository projectsRepository, ProjectMapper projectMapper, TokenProvider tokenProvider) {
        this.projectsRepository = projectsRepository;
        this.projectMapper = projectMapper;
        this.tokenService = tokenProvider;
    }

    public List<ProjectDto> getProjects(HttpServletRequest request) {
        List<Project> projects = (List<Project>) projectsRepository.findUserProjects(
            UUID.fromString(tokenService.getIdFromToken(request))
        );
        return projects.stream().map(project -> projectMapper.toDto(project)).collect(Collectors.toList()); 
    } 
}
