package com.example.renovations.works;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.renovations.projects.Project;
import com.example.renovations.projects.ProjectInfo;


@Mapper(componentModel = "spring")
public interface WorkMapper {
    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    @Mapping(source = "parentProject", target = "parentProject", qualifiedByName = "projectToProjectInfo")
    WorkDto toDto(Work work);

    @Qualifier
    @Named("projectToProjectInfo")
    public static ProjectInfo projectToProjectInfo(Project project) {
        return new ProjectInfo(project);
    }
}
