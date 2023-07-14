package io.realworld.angular.conduit.config;


import io.realworld.angular.conduit.exception.JwtTokeNotValidExceptions;
import io.realworld.angular.conduit.utility.JWTUtility;
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
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class JWTSecurityCheckFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token != null) {
            try {
                token = token.substring(6);
                if (jwtUtility.validate(token)) {
                    String username = jwtUtility.getUsername(token);
                    Collection<? extends GrantedAuthority> authorities = jwtUtility.getAuthorities(token);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new JwtTokeNotValidExceptions("Jwt token is not validated");
                }
            } catch (Exception e) {
                log.info("Exception occurred: ", e);
            }
        }
        filterChain.doFilter(request,response);
    }
}