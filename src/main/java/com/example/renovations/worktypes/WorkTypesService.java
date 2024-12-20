package com.example.renovations.worktypes;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class WorkTypesService {
    WorkTypesRepository workTypesRepository;

    WorkTypeMapper workTypeMapper;
    
    TokenProvider tokenService;

    @Autowired
    WorkTypesPermissions workTypesPermissions;

    WorkTypesService(WorkTypesRepository workTypesRepository, WorkTypeMapper workTypeMapper, TokenProvider tokenProvider) {
        this.workTypesRepository = workTypesRepository;
        this.workTypeMapper = workTypeMapper;
        this.tokenService = tokenProvider;
    }

    public List<WorkTypeDto> getWorkTypes(HttpServletRequest request) {
        UUID userId = UUID.fromString(tokenService.getIdFromToken(request));
        List<WorkType> workTypes = (List<WorkType>) workTypesRepository.findUserWorkTypes(userId);
        return workTypes.stream().map(work -> workTypeMapper.toDto(request, work, userId)).collect(Collectors.toList()); 
    } 

    public WorkTypeDto getWorkTypeById(HttpServletRequest request, String workId) {
        Optional<WorkType> workType = workTypesRepository.findById(UUID.fromString(workId));
        if (!workType.isPresent()) {
            return null;
        }
        return workTypeMapper.toDto(request, workType.get(), UUID.fromString(tokenService.getIdFromToken(request))); 
    } 

    public void patchWorkType(HttpServletRequest request, String workTypeId, WorkTypeDto workTypeDto) throws AccessDeniedException {
        Optional<WorkType> optWorkType = workTypesRepository.findById(UUID.fromString(workTypeId));
        if (optWorkType.isPresent()) {
            WorkType workType = optWorkType.get();
            if (workTypesPermissions.canUserUpdate(workTypeMapper.toDto(request, workType, UUID.fromString(tokenService.getIdFromToken(request))))) {
                workType.setLabel(workType.getLabel());
                workTypesRepository.save(workType);
            } else {
                throw new AccessDeniedException(null);
            }
        }
    } 
}
