package com.example.renovations.works;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.example.renovations.projects.Project;
import com.example.renovations.worktypes.WorkType;

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
@Entity(name = "works")
public class Work {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_type_id")
    private WorkType workType;

    private String label;

    private Date startDate;
    private Date endDate;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_project_id")
    private Project parentProject;

    public Work(
        WorkType workType,
        String label,
        String comment
        ) {
        this.workType = workType;
        this.label = label;
        this.comment = label;
    }

    @Override
    public String toString() {
        return String.format("Work [id=%d, label=%s, startDate=%s, endDate=%s, comment=%s, workType=%s]", id, label, startDate, endDate, comment, workType);
    }

}
