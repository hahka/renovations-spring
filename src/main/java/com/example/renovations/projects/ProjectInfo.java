package com.example.renovations.projects;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ProjectInfo {
    UUID id;

    String label;

    Date startDate;
    Date endDate;

    public ProjectInfo(Project project) {
        if (project != null) {
            setId(project.getId());
            setLabel(project.getLabel());
            setStartDate(project.getStartDate());
            setEndDate(project.getEndDate());
        }
    }

}
