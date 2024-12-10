package com.example.renovations.users;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private UUID id;
    private String username;

    public UserInfo(User user) {
        id = user.getId();
        username = user.getUsername();
    }
}
