package com.example.renovations.works;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkController {
    private final WorksRepository workRepository;
    private final WorksService worksService;

    WorkController(WorksRepository workRepository, WorksService worksService) {
      this.workRepository = workRepository;
      this.worksService = worksService;
    }

    @GetMapping("/works")
    public List<WorkDto> getWorks(HttpServletRequest request) {
        return worksService.getWorks(request);
    }

    @PostMapping("/works")
    void addWork(@RequestBody Work work) {
        workRepository.save(work);
    }
}