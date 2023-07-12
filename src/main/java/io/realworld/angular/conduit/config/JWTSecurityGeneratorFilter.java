package io.realworld.angular.conduit.config;


/*KERAK EMAS*/

import io.realworld.angular.conduit.exception.NotRegisteredException;
import io.realworld.angular.conduit.utility.JWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class JWTSecurityGeneratorFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility;

    public JWTSecurityGeneratorFilter(JWTUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }


    public String generate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return jwtUtility.generate(authentication.getName(),
                    authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );
        }

        throw new NotRegisteredException("User not registered");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request,response);
    }
}

