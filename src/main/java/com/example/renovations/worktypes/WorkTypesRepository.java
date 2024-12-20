package com.example.renovations.worktypes;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTypesRepository extends JpaRepository<WorkType, UUID> {
    @Query(
        "SELECT w FROM work_types w " + 
        "LEFT JOIN FETCH w.user u WHERE u.id = ?1 OR w.user IS NULL")
    List<WorkType> findUserWorkTypes(UUID userId); 
}