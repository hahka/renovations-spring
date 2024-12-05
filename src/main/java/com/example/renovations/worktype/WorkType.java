package com.example.renovations.worktype;

public enum WorkType {
    DEMOLITION("DEMO"), CLEANING("CLEANING");

    private String code;

    private WorkType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
