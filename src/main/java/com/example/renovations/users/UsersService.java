package com.example.renovations.users;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.renovations.config.auth.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsersService {
    
  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Autowired
  TokenProvider tokenService;

  UsersService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserDto getUser(String username) throws AccessDeniedException {
    User user = (User) userRepository.findByUsername(username);
    System.out.println(user.getId());
    return userMapper.toDto(user); 
  }

  public UserDto getCurrentUser(HttpServletRequest request) {
    UUID uuid = UUID.fromString(tokenService.getIdFromToken(request));
    final Optional<User> user = userRepository.findById(uuid);
    if (!user.isPresent()) {
      // TODO: improve
      return null;
    }
    return userMapper.toDto(user.get()); 
  }
}
