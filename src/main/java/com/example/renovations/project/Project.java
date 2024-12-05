package com.example.renovations.project;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.example.renovations.users.User;
import com.example.renovations.works.Work;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "user_id"))
                */
    private List<User> users;
    

    private String label;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "parentProject", fetch = FetchType.LAZY)
    private List<Work> works;

    public Project(String label) {
        this.label = label;
    }
}
