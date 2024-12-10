package com.example.renovations.projects;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<Project, Long>{
    @Query("SELECT p FROM projects p JOIN FETCH p.users u WHERE u.id = ?1")
    List<Project> findUserProjects(UUID userId);
}
