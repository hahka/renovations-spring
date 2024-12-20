package com.example.renovations.worktypes;

import java.util.UUID;

import lombok.Data;

@Data
public class WorkTypeDto {
    UUID id;

    String label;

    boolean canUpdate;
}
