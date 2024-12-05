package com.example.renovations.works;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkController {
    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    WorkController(WorkRepository workRepository, WorkMapper workMapper) {
      this.workRepository = workRepository;
      this.workMapper = workMapper;
    }

    @GetMapping("/works")
    public List<WorkDto> getWorks() {
        List<Work> works = (List<Work>) workRepository.findAll();
        return works.stream().map(work -> workMapper.toDto(work)).collect(Collectors.toList()); 
    }

    @PostMapping("/works")
    void addWork(@RequestBody Work work) {
        workRepository.save(work);
    }
}