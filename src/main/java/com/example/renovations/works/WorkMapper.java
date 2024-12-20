package com.example.renovations.works;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.renovations.projects.Project;
import com.example.renovations.projects.ProjectInfo;
import com.example.renovations.worktypes.WorkType;
import com.example.renovations.worktypes.WorkTypeInfo;


@Mapper(componentModel = "spring")
public interface WorkMapper {
    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    @Mapping(source = "parentProject", target = "parentProject", qualifiedByName = "projectToProjectInfo")
    @Mapping(source = "workType", target = "workType", qualifiedByName = "workTypeToWorkTypeInfo")
    WorkDto toDto(Work work);

    @Qualifier
    @Named("projectToProjectInfo")
    public static ProjectInfo projectToProjectInfo(Project project) {
        return new ProjectInfo(project);
    }
    
    @Qualifier
    @Named("workTypeToWorkTypeInfo")
    public static WorkTypeInfo workTypeToWorkTypeInfo(WorkType workType) {
        return new WorkTypeInfo(workType);
    }
}
