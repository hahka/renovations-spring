package com.example.renovations.projects;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.renovations.users.User;
import com.example.renovations.users.UserInfo;
import com.example.renovations.works.Work;
import com.example.renovations.works.WorkInfo;


@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "works", target = "works", qualifiedByName = "worksToWorksInfo")
    @Mapping(source = "users", target = "users", qualifiedByName = "usersToUsersInfo")
    ProjectDto toDto(Project project);

    @Qualifier
    @Named("worksToWorksInfo")
    public static Set<WorkInfo> worksToWorksInfo(List<Work> works) {
        if (works == null) {
            return null;
        }
        return works.stream().map(work -> new WorkInfo(work)).collect(Collectors.toSet());
    }

    @Qualifier
    @Named("usersToUsersInfo")
    public static Set<UserInfo> usersToUsersInfo(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(user -> new UserInfo(user)).collect(Collectors.toSet());
    }
}
