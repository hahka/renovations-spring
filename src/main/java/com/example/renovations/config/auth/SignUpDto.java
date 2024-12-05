package com.example.renovations.config.auth;

import com.example.renovations.users.UserRole;

public record SignUpDto(
    String username,
    String password,
    UserRole role) {
}