package com.example.renovations.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.renovations.users.UserRepository;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final com.example.renovations.users.User user = this.repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return User.withUsername(username)
            .password(user.getPassword())
            // .authorities("ROLE_USER")
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }

  public UserDetails signUp(SignUpDto data) throws JWTCreationException {
    if (repository.findByUsername(data.username()) != null) {
      throw new JWTCreationException("Username already exists", null);
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    return repository.save(new com.example.renovations.users.User(data.username(), encryptedPassword, data.role()));
  }
}
