package com.example.renovations.users;


import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UsersService usersService;

    UserController(UsersService usersService) {
      this.usersService = usersService;
    }

    @GetMapping("/users")
    public ResponseEntity<UserDto> getUser(@RequestParam String username) throws AccessDeniedException {
      try { 
        UserDto userDto = usersService.getUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
      }
    }

    @GetMapping("/users/me")
    public UserDto getCurrentUser(HttpServletRequest request) {
        return usersService.getCurrentUser(request);
    }
}
