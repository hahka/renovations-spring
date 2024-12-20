package com.example.renovations.worktypes;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.renovations.users.User;

import jakarta.servlet.http.HttpServletRequest;


@Mapper(componentModel = "spring")
public interface WorkTypeMapper {
    WorkTypeMapper INSTANCE = Mappers.getMapper(WorkTypeMapper.class);

    @Mapping(target = "canUpdate", ignore = true)
    WorkTypeDto toDto(@Context HttpServletRequest request, WorkType workType, @Context UUID userId);

    @AfterMapping
    default void afterMapping(WorkType workType, @MappingTarget WorkTypeDto workTypeDto, @Context HttpServletRequest request, @Context UUID userId) {
        User workTypeUser = workType.getUser();
        workTypeDto.canUpdate = workTypeUser != null && workTypeUser.getId().equals(userId);
    }
}
