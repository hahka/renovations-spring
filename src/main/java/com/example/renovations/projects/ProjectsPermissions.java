package com.example.renovations.projects;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ProjectsPermissions {

    @Autowired
    TokenProvider tokenService;

    public boolean ownsProject(HttpServletRequest request, ProjectDto projectDto){
        UUID userId = UUID.fromString(tokenService.getIdFromToken(request));

        return projectDto.getUsers().stream()
        .filter(u -> u.getId().equals(userId))
        .findFirst().isPresent();
        
    }
}
