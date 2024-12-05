package com.example.renovations.users;

import java.util.Set;

import com.example.renovations.project.ProjectInfo;

import lombok.Data;

@Data
public class UserDto {
  private Long id;

  private String username;

  private Set<ProjectInfo> projects;

}
