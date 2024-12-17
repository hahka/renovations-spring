package com.example.renovations.config.permissions;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.renovations.users.User;

@Component
public class AuthenticationImpl extends AbstractAuthenticationToken {

    User authenticatedUser;

    public AuthenticationImpl(Collection<? extends GrantedAuthority> authorities, User authenticatedUser) {
        super(authorities);
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public Object getCredentials() {
        return this.authenticatedUser.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return this.authenticatedUser;
    }

}
