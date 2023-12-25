package ru.itis.hotelservice.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.hotelservice.security.authentication.ApiKeyAuthentication;

import java.io.IOException;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY_PARAMETER_NAME = "api-key";

    AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getParameter(API_KEY_PARAMETER_NAME);

        if (apiKey != null) {
            SecurityContextHolder.getContext().setAuthentication(
                    authenticationManager.authenticate(new ApiKeyAuthentication(AuthorityUtils.NO_AUTHORITIES, apiKey))
            );
        }

        filterChain.doFilter(request, response);
    }
}

