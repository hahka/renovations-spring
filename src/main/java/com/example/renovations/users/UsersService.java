package com.example.renovations.users;

import org.springframework.stereotype.Service;

@Service
public class UsersService {
    
  private final UserRepository userRepository;

  private final UserMapper userMapper;

  UsersService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserDto getUser(String username) {
    User user = (User) userRepository.findByUsername(username);
    return userMapper.toDto(user); 
  }
}
