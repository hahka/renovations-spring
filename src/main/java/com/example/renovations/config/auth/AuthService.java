package com.example.renovations.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.renovations.users.User;
import com.example.renovations.users.UserRepository;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    var user = repository.findByUsername(username);
    return user;
  }

  public UserDetails signUp(SignUpDto data) throws JWTCreationException {
    if (repository.findByUsername(data.username()) != null) {
      throw new JWTCreationException("Username already exists", null);
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.username(), encryptedPassword, data.role());
    return repository.save(newUser);
  }
}
