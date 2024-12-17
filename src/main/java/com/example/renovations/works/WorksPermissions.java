package com.example.renovations.works;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class WorksPermissions {

    @Autowired
    TokenProvider tokenService;

    public boolean ownsWork(HttpServletRequest request, WorkDto workDto){
        UUID userId = UUID.fromString(tokenService.getIdFromToken(request));

        return workDto.parentProject
            .getUsers().stream().filter(u -> u.getId().equals(userId))
            .count() > 0;
        
    }
}

