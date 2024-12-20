package com.example.renovations.worktypes;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.example.renovations.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "work_types")
public class WorkType {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
 