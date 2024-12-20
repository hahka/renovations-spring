package com.example.renovations.works;

import java.sql.Date;
import java.util.UUID;

import com.example.renovations.projects.ProjectInfo;
import com.example.renovations.worktypes.WorkTypeInfo;

import lombok.Data;

@Data
public class WorkDto {
    UUID id;

    WorkTypeInfo workType;

    String label;

    Date startDate;
    Date endDate;

    String comment;

    ProjectInfo parentProject;

}
