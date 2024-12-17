package com.example.renovations.works;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorksRepository extends CrudRepository<Work, UUID> {
    @Query(
        "SELECT w from works w " + 
        "JOIN FETCH w.parentProject p " +
        "JOIN FETCH p.users u WHERE u.id = ?1")
    List<Work> findUserWorks(UUID userId);
}
