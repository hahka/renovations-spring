package com.example.renovations.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String login;

    public UserInfo(User user) {
        id = user.getId();
        login = user.getLogin();
    }
}
