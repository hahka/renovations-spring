package com.example.renovations.config.auth;

import com.example.renovations.users.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role) {
}