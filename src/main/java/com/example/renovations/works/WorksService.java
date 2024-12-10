package com.example.renovations.works;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class WorksService {
    WorksRepository worksRepository;

    WorkMapper workMapper;
    
    TokenProvider tokenService;

    WorksService(WorksRepository worksRepository, WorkMapper workMapper, TokenProvider tokenProvider) {
        this.worksRepository = worksRepository;
        this.workMapper = workMapper;
        this.tokenService = tokenProvider;
    }

    public List<WorkDto> getWorks(HttpServletRequest request) {
        List<Work> works = (List<Work>) worksRepository.findUserWorks(
            UUID.fromString(tokenService.getIdFromToken(request))
        );
        return works.stream().map(work -> workMapper.toDto(work)).collect(Collectors.toList()); 
    } 
}
