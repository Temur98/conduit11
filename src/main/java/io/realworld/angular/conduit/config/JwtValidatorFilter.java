package io.realworld.angular.conduit.config;

import io.realworld.angular.conduit.config.utility.JwtUtility;
import io.realworld.angular.conduit.exception.TokenVException;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtValidatorFilter extends OncePerRequestFilter {
        private final JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
                String val = request.getHeader("Authorization");
                if (val != null){
                    val = val.substring(val.indexOf(" ") + 1);
                    try {
                        if (jwtUtility.valiDate(val)) {
                            String userName = jwtUtility.getUserName(val);
                            Collection<? extends GrantedAuthority> authorities = jwtUtility.getAuthorities(val);
                            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
                            SecurityContextHolder.getContext().setAuthentication(authentication);

                        } else {
                            throw new TokenVException("Token not nooooot at all valid");
                        }
                    }catch (Exception errror){
                        log.info("Token validation exception {}", errror.getMessage());
                    }finally {
                        filterChain.doFilter(request, response);
                    }
                }
                else {
                    filterChain.doFilter(request, response);
                }



    }
}
