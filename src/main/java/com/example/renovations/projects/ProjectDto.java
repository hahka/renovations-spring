package com.example.renovations.projects;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import com.example.renovations.users.UserInfo;
import com.example.renovations.works.WorkInfo;

import lombok.Data;

@Data
public class ProjectDto {
    UUID id;

    String label;

    Date startDate;
    Date endDate;

    Set<WorkInfo> works;

    Set<UserInfo> users;
}
