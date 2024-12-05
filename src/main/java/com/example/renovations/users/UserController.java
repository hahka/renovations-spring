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
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserController(UserRepository userRepository, UserMapper userMapper) {
      this.userRepository = userRepository;
      this.userMapper = userMapper;
    }

    @GetMapping("/user")
    public UserDto getUser(@RequestParam String username) {
        User user = (User) userRepository.findByUsername(username);
        return userMapper.toDto(user); 
    }
}
