package com.example.renovations.config.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.renovations.users.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenProvider {
  @Value("${security.jwt.token.secret-key}")
  private String JWT_SECRET;

  public String generateAccessToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
      return JWT.create()
          .withSubject(user.getId().toString())
          .withClaim("username", user.getUsername())
          .withExpiresAt(genAccessExpirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new JWTCreationException("Error while generating token", exception);
    }
  }

  /*public String getUsernameFromToken(HttpServletRequest request) {
    return this.validateToken(this.recoverToken(request)).getClaim("username").asString();
  }

  public String getIdFromToken(HttpServletRequest request) {
    return this.validateToken(this.recoverToken(request)).getSubject();
  }*/

  public String getUsernameFromToken(HttpServletRequest request) {
    var cookies = request.getCookies();
    if (cookies == null) {
      return null; // TODO: throw
    }

    var tokenCookie = Arrays.stream(cookies)
      .filter(cookie -> cookie.getName().equals("accessToken"))
      .map(cookie -> cookie.getValue()).findFirst();

    if (tokenCookie.isPresent()) {
      return this.validateToken(tokenCookie.get()).getClaim("username").asString();
    } else {
      throw new IllegalStateException("accessToken is missing");
    }
  }

  public String getIdFromToken(HttpServletRequest request) {
    var tokenCookie = Arrays.stream(request.getCookies())
      .filter(cookie -> cookie.getName().equals("accessToken"))
      .map(cookie -> cookie.getValue()).findFirst();

    if (tokenCookie.isPresent()) {
      return this.validateToken(tokenCookie.get()).getSubject();
    } else {
      throw new IllegalStateException("accessToken is missing");
    }
    
    // return this.validateToken(this.recoverToken(request)).getSubject();
  }

  /*
  public String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");
  }
  */

  private DecodedJWT validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
      return JWT.require(algorithm)
          .build()
          .verify(token);
    } catch (JWTVerificationException exception) {
      throw new JWTVerificationException("Error while validating token", exception);
    }
  }

  private Instant genAccessExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
