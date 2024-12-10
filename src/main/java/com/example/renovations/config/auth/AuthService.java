package com.example.renovations.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
      final User user = this.repository.findByUsername(username);
      if(user == null) {
          throw new UsernameNotFoundException("Unknown user "+ username);
      }
      // Need to create a new user to remove projects, which are not needed and won't be available later du to lazy loading
      return new User(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

  public UserDetails signUp(SignUpDto data) throws JWTCreationException {
    if (repository.findByUsername(data.username()) != null) {
      throw new JWTCreationException("Username already exists", null);
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    return repository.save(new User(data.username(), encryptedPassword, data.role()));
  }
}
