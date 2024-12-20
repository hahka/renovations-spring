package com.example.renovations.worktypes;

import java.util.UUID;

import lombok.Data;

@Data
public class WorkTypeInfo {
    UUID id;

    String label;

    public WorkTypeInfo(WorkType workType) {
        setId(workType.getId());
        setLabel(workType.getLabel());
    }
}
