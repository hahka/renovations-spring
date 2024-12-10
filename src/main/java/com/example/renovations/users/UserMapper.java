package com.example.renovations.users;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.renovations.projects.Project;
import com.example.renovations.projects.ProjectInfo;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "projects", target = "projects", qualifiedByName = "projectsToProjectsInfo")
    UserDto toDto(User user);

    @Qualifier
    @Named("projectsToProjectsInfo")
    public static Set<ProjectInfo> projectsToProjectsInfo(List<Project> projects) {
        if (projects == null) {
            return null;
        }
        return projects.stream().map(project -> new ProjectInfo(project)).collect(Collectors.toSet());
    }
}
