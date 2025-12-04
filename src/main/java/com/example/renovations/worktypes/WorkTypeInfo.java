package com.example.renovations.worktypes;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTypeInfo {
    UUID id;

    String label;

    public WorkTypeInfo(WorkType workType) {
        setId(workType.getId());
        setLabel(workType.getLabel());
    }
}
