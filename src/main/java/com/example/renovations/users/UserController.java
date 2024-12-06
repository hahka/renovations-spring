package com.example.renovations.users;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UsersService usersService;

    UserController(UsersService usersService) {
      this.usersService = usersService;
    }

    @GetMapping("/user")
    public UserDto getUser(@RequestParam String username) {
    return usersService.getUser(username);
    }
}
