package com.example.renovations.users;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.renovations.projects.Project;
import com.example.renovations.worktypes.WorkType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Component
@Builder
@Data()
@Entity(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
  private List<Project> projects;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<WorkType> workTypes;

  protected User() {}

  public User(UUID id, String username, String password, UserRole role, List<Project> projects, List<WorkType> workTypes) {
    this.id = id; 
    this.username = username;
    this.password = password;
    this.role = role;
    this.projects = projects;
    this.workTypes = workTypes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ROLE_ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    }
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}