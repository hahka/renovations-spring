package com.example.renovations.projects;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;
import com.example.renovations.users.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProjectsService {
    ProjectsRepository projectsRepository;

    ProjectMapper projectMapper;
    
    TokenProvider tokenService;

    @Autowired
    ProjectsPermissions projectsPermissions;

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

    public ProjectDto getProjectById(HttpServletRequest request, String projectId) {
        Optional<Project> project = projectsRepository.findById(UUID.fromString(projectId));
        if (!project.isPresent()) {
            return null;
        }
        return projectMapper.toDto(project.get()); 
    } 

    public void createProject(HttpServletRequest request, Project project) {
        List<User> list = new ArrayList<User>(); 
        list.add(new User(UUID.fromString(tokenService.getIdFromToken(request))));
        project.setUsers(list);
        projectsRepository.save(project);
    } 

    public void patchProject(HttpServletRequest request, String projectId, ProjectDto projectDto) throws AccessDeniedException {
        Optional<Project> optProject = projectsRepository.findById(UUID.fromString(projectId));
        if (optProject.isPresent()) {
            Project project = optProject.get();
            if (projectsPermissions.ownsProject(request, projectMapper.toDto(project))) {

                project.setLabel(projectDto.getLabel());
                project.setStartDate(projectDto.getStartDate());
                project.setEndDate(projectDto.getEndDate());
                projectsRepository.save(project);
            } else {
                throw new AccessDeniedException(null);
            }
        }
    } 
}
