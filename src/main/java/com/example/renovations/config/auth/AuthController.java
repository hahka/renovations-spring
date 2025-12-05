package com.example.renovations.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.renovations.users.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AuthService service;
  @Autowired
  private TokenProvider tokenService;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data) {
    service.signUp(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * If URL changes, remember to change shouldNotFilter function in SecurityFilter
   * @param data
   * @param response
   * @return
   */
  @PostMapping("/signin")
  public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data, HttpServletResponse response) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var authentication = authenticationManager.authenticate(usernamePassword);
    var accessToken = tokenService.generateAccessToken((User) authentication.getPrincipal());
    Cookie cookie = new Cookie("accessToken", accessToken);
    cookie.setHttpOnly(true);
    cookie.setPath("/"); // TODO: Needed in order to set the cookie. Change for prod
    response.addCookie(cookie);
    // return ResponseEntity.status(HttpStatus.OK).headers(null).body(new JwtDto(accessToken)).build();
    return ResponseEntity.ok(new JwtDto(accessToken));
  }

  
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser(HttpServletResponse response) {
    Cookie cookie = new Cookie("accessToken", "");
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}