package com.example.renovations.projects;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, UUID>{
    @Query("SELECT p FROM projects p JOIN FETCH p.users u WHERE u.id = ?1")
    Page<Project> findUserProjects(UUID userId, Pageable p);
}
