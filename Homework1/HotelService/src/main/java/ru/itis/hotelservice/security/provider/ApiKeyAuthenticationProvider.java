package ru.itis.hotelservice.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.itis.hotelservice.security.authentication.ApiKeyAuthentication;

import java.util.Collections;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("app.api.authentication.key")
    private String apiKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!authentication.getPrincipal().equals(apiKey)) {
            throw new BadCredentialsException("Invalid API KEY");
        }

        return new UsernamePasswordAuthenticationToken(apiKey, null,
                Collections.singleton(new SimpleGrantedAuthority("TRUSTED_SERVICE")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
