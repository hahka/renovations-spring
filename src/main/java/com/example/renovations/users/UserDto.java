package com.example.renovations.users;

import java.util.Set;
import java.util.UUID;

import com.example.renovations.projects.ProjectInfo;
import com.example.renovations.worktypes.WorkTypeInfo;

import lombok.Data;

@Data
public class UserDto {
  private UUID id;

  private String username;

  private Set<ProjectInfo> projects;

  private Set<WorkTypeInfo> workTypes;
}
