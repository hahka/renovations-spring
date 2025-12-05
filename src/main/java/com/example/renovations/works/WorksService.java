package com.example.renovations.works;

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

import jakarta.servlet.http.HttpServletRequest;

@Service
public class WorksService {
    WorksRepository worksRepository;

    WorkMapper workMapper;
    
    TokenProvider tokenService;

    @Autowired
    WorksPermissions worksPermissions;

    WorksService(WorksRepository worksRepository, WorkMapper workMapper, TokenProvider tokenProvider) {
        this.worksRepository = worksRepository;
        this.workMapper = workMapper;
        this.tokenService = tokenProvider;
    }

    public Page<WorkDto> getWorks(HttpServletRequest request, String search, Pageable p) {
        Page<Work> works = worksRepository.findUserWorks(
            UUID.fromString(tokenService.getIdFromToken(request)), search, p
        );
        return works.map((work) -> workMapper.toDto(work));
    } 

    public WorkDto getWorkById(HttpServletRequest request, String workId) {
        Optional<Work> work = worksRepository.findById(UUID.fromString(workId));
        if (!work.isPresent()) {
            return null;
        }
        return workMapper.toDto(work.get()); 
    } 

    public void patchWork(HttpServletRequest request, String workId, WorkDto workDto) throws AccessDeniedException {
        System.out.println("TEST 00");
        Optional<Work> optWork = worksRepository.findById(UUID.fromString(workId));
        if (optWork.isPresent()) {
            System.out.println("TEST 01");
            Work work = optWork.get();
            System.out.println("TEST 02");
            if (worksPermissions.ownsWork(request, workMapper.toDto(work))) {
                
                System.out.println("TEST 03");
                work.setLabel(workDto.getLabel());
                work.setStartDate(workDto.getStartDate());
                work.setEndDate(workDto.getEndDate());
                work.setComment(workDto.getComment());
                worksRepository.save(work);
            } else {
                throw new AccessDeniedException(null);
            }
        }
    } 
}
