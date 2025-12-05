package com.example.renovations.works;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface WorksRepository extends JpaRepository<Work, UUID> {
    @Query(
        "SELECT w from works w " + 
        "JOIN FETCH w.parentProject p " +
        "JOIN FETCH p.users u " +
        "WHERE u.id = ?1 AND lower(p.label) LIKE lower(concat('%', ?2,'%'))")
    Page<Work> findUserWorks(UUID userId, String search, Pageable p);
}
