package io.realworld.angular.conduit.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.realworld.angular.conduit.exception.JwtTokeNotValidExceptions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Service
public class JWTUtility {
    private static final Integer expireInMs = 60000 * 60 * 24;

    private static final String SECRET_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    private final static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generate(String username, String authorities) {
        return Jwts.builder()
                .setSubject("FOR-LOGIN")
                .setIssuer("MEDIUM")
                .claim("username", username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireInMs))
                .signWith(key)
                .compact();
    }

    public boolean validate(String token) throws ExpiredJwtException{

        return !isExpired(token)
        && getUsername(token) != null
                && getAuthorities(token).size() > 0;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username", String.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = getClaims(token);
        return Arrays.stream(claims.get("authorities", String.class)
                        .split(", ")).
                map(SimpleGrantedAuthority::new)
                .toList();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
