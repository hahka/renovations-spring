package com.example.renovations.config.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        
    }

    //Access Denied / unauthorized has handle method when failures occur
    /*@Override
    public void handle(ServerWebExchange exchange, AccessDeniedException accessDeniedException) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return new AuthFailureHandler().formatResponse(response);
    }*/
}