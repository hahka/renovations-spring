package com.example.renovations.worktypes;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;
import com.example.renovations.users.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class WorkTypesService {
    WorkTypesRepository workTypesRepository;

    WorkTypeMapper workTypeMapper;

    TokenProvider tokenService;

    @Autowired
    WorkTypesPermissions workTypesPermissions;

    WorkTypesService(WorkTypesRepository workTypesRepository, WorkTypeMapper workTypeMapper,
            TokenProvider tokenProvider) {
        this.workTypesRepository = workTypesRepository;
        this.workTypeMapper = workTypeMapper;
        this.tokenService = tokenProvider;
    }

    public Page<WorkTypeDto> getWorkTypes(HttpServletRequest request, Pageable p) {
        UUID userId = UUID.fromString(tokenService.getIdFromToken(request));
        Page<WorkType> workTypes = workTypesRepository.findUserWorkTypes(userId, p);
        return workTypes.map(work -> workTypeMapper.toDto(request, work, userId));
    }

    public WorkTypeDto getWorkTypeById(HttpServletRequest request, String workId) throws AccessDeniedException {
        Optional<WorkType> optWorkType = workTypesRepository.findById(UUID.fromString(workId));
        if (!optWorkType.isPresent()) {
            throw new AccessDeniedException(null);
        }
        WorkType workType = optWorkType.get();
        User workTypeUser = workType.getUser();
        UUID userId = UUID.fromString(tokenService.getIdFromToken(request));
        if (workTypeUser != null && !workTypeUser.getId().equals(userId)) {
            throw new AccessDeniedException(null);
        }
        return workTypeMapper.toDto(request, workType, userId);
    }

    public void postWorkType(HttpServletRequest request, WorkType workType) {
        workType.setUser(User.builder()
                .id(UUID.fromString(tokenService.getIdFromToken(request)))
                .build());
        workTypesRepository.save(workType);
    }

    public void patchWorkType(HttpServletRequest request, String workTypeId, WorkTypeDto workTypeDto)
            throws AccessDeniedException {
        Optional<WorkType> optWorkType = workTypesRepository.findById(UUID.fromString(workTypeId));

        if (optWorkType.isPresent()) {
            WorkType workType = optWorkType.get();
            if (workTypesPermissions.canUserUpdate(
                    workTypeMapper.toDto(request, workType, UUID.fromString(tokenService.getIdFromToken(request))))) {
                workType.setLabel(workTypeDto.getLabel());
                workTypesRepository.save(workType);
            } else {
                throw new AccessDeniedException(null);
            }
        } else {
            throw new AccessDeniedException(null);
        }
    }
}
