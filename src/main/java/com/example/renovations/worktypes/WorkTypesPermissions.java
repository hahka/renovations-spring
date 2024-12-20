package com.example.renovations.worktypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.renovations.config.auth.TokenProvider;

@Component
public class WorkTypesPermissions {

    @Autowired
    TokenProvider tokenService;

    public boolean canUserUpdate(WorkTypeDto workTypeDto){
        return workTypeDto.canUpdate;
    }
}

