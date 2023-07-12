package io.realworld.angular.conduit.config;

import io.realworld.angular.conduit.customexseption.TokenValidationException;
import io.realworld.angular.conduit.utility.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtValidatorFilter extends OncePerRequestFilter {
    private final JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String value = request.getHeader("Authorization");
        if (value != null) {
            value = value.substring(value.indexOf(" ") + 1);
            try {
                if (jwtUtility.validate(value)) {
                    String username = jwtUtility.getUsername(value);
                    Collection<? extends GrantedAuthority> authorities = jwtUtility.getAuthorities(value);
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    throw new TokenValidationException("token not valid");
                }
            } catch (Exception ex) {
                log.info("token validation exception {}", ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
