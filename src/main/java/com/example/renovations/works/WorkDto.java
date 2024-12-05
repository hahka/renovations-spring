package com.example.renovations.works;

import java.sql.Date;

import com.example.renovations.project.ProjectInfo;
import com.example.renovations.worktype.WorkType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Data;


@Data
public class WorkDto {
    long id;

    WorkType[] workTypes;

    String label;

    Date startDate;
    Date endDate;

    String comment;

    ProjectInfo parentProject;

}
