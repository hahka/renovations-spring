package com.example.renovations.works;

import java.sql.Date;

import com.example.renovations.worktype.WorkType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class WorkInfo {
    private long id;

    private WorkType[] workTypes;

    private String label;

    private Date startDate;
    private Date endDate;

    private String comment;
    
    public WorkInfo(Work work) {
        if (work != null) {
            setId(work.getId());
            setLabel(work.getLabel());
            setWorkTypes(work.getWorkTypes());
            setStartDate(work.getStartDate());
            setEndDate(work.getEndDate());
            setComment(work.getComment());
        }
    }
}
