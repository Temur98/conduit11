package io.realworld.angular.conduit.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization != null){
            String token = authorization.substring(authorization.indexOf(" ")+1);
            if (jwtService.isValidToken(token)){
                String username = jwtService.getUsername(token);
                Authentication authentication = new  UsernamePasswordAuthenticationToken(username, null, jwtService.getAuthorities(token));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                try {
                    throw new Exception("Token not valid");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
