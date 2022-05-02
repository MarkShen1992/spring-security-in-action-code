package io.github.config;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication)
        throws AuthenticationException {
        String userName = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if ("shenjy".equals(userName) && "shenjy".equals(password)) {
            return new UsernamePasswordAuthenticationToken(userName, password, Arrays.<GrantedAuthority>asList());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
