package com.example.renovations.worktype;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WorkTypeConverter implements AttributeConverter<WorkType, String> {
    @Override
    public String convertToDatabaseColumn(WorkType workType) {
        if (workType == null) {
            return null;
        }

        return workType.getCode();
    }

    @Override
    public WorkType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(WorkType.values())
        .filter(w -> w.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
}
